package cn.com.egova.utils;

import org.apache.axis.encoding.Base64;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;

public class CtRsaUtils {
	public static final String KEY_ALGORITHM = "RSA";
	public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
	public static final String PUBLIC_KEY = "pub_key";
	public static final String PRIVATE_KEY = "pri_key";
	private static final int MAX_ENCRYPT_BLOCK = 117;
	private static final int MAX_DECRYPT_BLOCK = 128;
	public static String publicKey_child = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCxx57XjA3JCTZT8We3jM8Ug5h+JUzHg+iNxL6VSmnfEsOGqULyBvWWO2R3lCikzpbFNMgTqeN9gYXjBHVNWpY5zKl6RTZ/mxIE16Vws2iHSZYo/R+Mv9pv/5hhPZdTl9hFR2SkpnjGPxooWZTA+rT98ifEHaJQWSP9woB+wSwwIDAQAB";
	public static String privateKey_parent = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALHHnteMDckJNlPxZ7eMzxSDmH4lTMeD6I3EvpVKad8Sw4apQvIG9ZY7ZHeUKKTOlsU0yBOp432BheP74EdU1aljnMqXpFNn+bEgTXpXCzaIdJlij9H4y/2m//mGE9l1OX2EVHZKSmeMY/GihZlMD6tP3yJ8QdolBZI/3CgH7BLDAgMBAAECgYAvkQioBXoeww89MIcerlct1vPzNImxjFKps+2GRk3DeOLF4f3eggwtsSB1ejfRuNDQXQn3cOpER2aKlHbyvvkXkNhrd/lCjpk6wtDYQsq/eeQ7wC8Am6hQ2d8cKySCl5LrpHHzkGkTv1DHw7rNKrMR03ahJWXsyPcqrbhvBMwrMQJBAPlh95E8wPSsqqYA/74o7Iqxa7nq9osXT6t5xrJc2CpI2go4OK1Da1zOI+mCbNpnuA7PnWu9xam2cCmNAsTHGskCQQC2f0L3no9mtGmuB7M7xN4Me5pUlZqVRWzLKDUK3IPEHzUZs7WDQ77RqOJBrvdHxFpY3ZS+bDFYouUbck39vHsrAkEAiIgCKhnA6jO+GbRiT5HILwaDm/3vjKbuj0rUZcI+9qd7+CxfmzxWAzE4qBcn0UsHkdRIszvqg8fGEHmLEoCPQQJAZWT3lBRooCuEu8hTcNXEeTMDYBNuu5jDBWzla49xNjoQiqMqKjAtiNdIPi4z/Ykrkpt1LtZ825dTJg2qUp2QJAMdlZbhYPL99fjhsbUS+xNisTczoi9Y+PEh+expEvfnTIj/YqKHtVdCdIPxktews831vU14GF+UwWFEZQYLt65w==";

	public CtRsaUtils() {
	}

	public static String sign(String content, String privateKey) throws Exception {
		byte[] keyBytes = decryptBASE64(privateKey);
		byte[] data = content.getBytes(Charset.forName("UTF-8"));
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		Signature signature = Signature.getInstance("MD5withRSA");
		signature.initSign(privateK);
		signature.update(data);
		return encryptBASE64(signature.sign());
	}

	public static boolean verify(String content, String publicKey, String sign) throws Exception {
		byte[] keyBytes = decryptBASE64(publicKey);
		byte[] data = content.getBytes(Charset.forName("UTF-8"));
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicK = keyFactory.generatePublic(keySpec);
		Signature signature = Signature.getInstance("MD5withRSA");
		signature.initVerify(publicK);
		signature.update(data);
		return signature.verify(decryptBASE64(sign));
	}

	public static String decryptByPublicKey(String content, String publicKey) throws Exception {
		byte[] keyBytes = decryptBASE64(publicKey);
		byte[] encryptedData = decryptBASE64(content);
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		Key publicK = keyFactory.generatePublic(x509KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(2, publicK);
		int inputLen = encryptedData.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;

		for(int i = 0; inputLen - offSet > 0; offSet = i * 128) {
			byte[] cache;
			if (inputLen - offSet > 128) {
				cache = cipher.doFinal(encryptedData, offSet, 128);
			} else {
				cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
			}

			out.write(cache, 0, cache.length);
			++i;
		}

		byte[] decryptedData = out.toByteArray();
		out.close();
		return new String(decryptedData, "utf-8");
	}

	public static String encryptByPrivateKey(String content, String privateKey) throws Exception {
		byte[] keyBytes = decryptBASE64(privateKey);
		byte[] data = content.getBytes(Charset.forName("UTF-8"));
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(1, privateK);
		int inputLen = data.length;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int offSet = 0;

		for(int i = 0; inputLen - offSet > 0; offSet = i * 117) {
			byte[] cache;
			if (inputLen - offSet > 117) {
				cache = cipher.doFinal(data, offSet, 117);
			} else {
				cache = cipher.doFinal(data, offSet, inputLen - offSet);
			}

			out.write(cache, 0, cache.length);
			++i;
		}

		byte[] encryptedData = out.toByteArray();
		out.close();
		return encryptBASE64(encryptedData);
	}

	public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
		Key key = (Key)keyMap.get("pri_key");
		return encryptBASE64(key.getEncoded());
	}

	public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
		Key key = (Key)keyMap.get("pub_key");
		return encryptBASE64(key.getEncoded());
	}

	public static byte[] decryptBASE64(String key) throws Exception {
		return Base64.decode(key);
	}

	public static String encryptBASE64(byte[] key) throws Exception {
		return Base64.encode(key);
	}
}
