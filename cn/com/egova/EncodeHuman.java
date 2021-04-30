package cn.com.egova;

import cn.com.egova.utils.ECBUtils;

public class EncodeHuman {
	
	public static final String KEY_3DESDECODE = "0123456789egova9876543210123456789egova9876543210123456789egova9876543210123456789egova9876543210123456789egova98765432101234567";
	public static final String KEY_SECRET = "0123456789egova98765432101234567";
	public static final String DOT = ",";
	
	@org.junit.Test
	public  void testECB() throws Exception {
		StringBuilder EncodedHumanInfo = new StringBuilder();
		
		//拼接    human_id,KEY_SECRET,当前时间    形式，就这里修改不同人员得humanid就是
		EncodedHumanInfo=EncodedHumanInfo.append("101877").append(DOT).append(KEY_SECRET).append(DOT).append(System.currentTimeMillis());

		byte[] bytes = ECBUtils.des3EncodeECB(KEY_3DESDECODE.getBytes(), EncodedHumanInfo.toString().getBytes());
		String encoded = ECBUtils.bytesToHexString(bytes);
		System.out.println(encoded);

	}
}
