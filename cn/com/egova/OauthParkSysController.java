package cn.com.egova;

import cn.com.egova.bizbase.tools.HttpUtils;
import cn.com.egova.utils.CtRsaUtils;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class OauthParkSysController {

	private static final Logger logger = LoggerFactory.getLogger(OauthParkSysController.class);

	private static String URL = "http://dev.tongtongtingche.com.cn/apiplatform/parkopen/urbanpark/apitest/AA83D8BE696/";//开发环境
	private static String accessID = "TONGb7f21d6cc121d41c";
	private static String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKiXNRC5dSmcF6cP3Ap9rMBynNVKtfHuQ8tuc0pVspZGyzLKX1n0QkuU+Ui6yXoAuifNwKHiyDoAnsMJasgaa7vaM5oGau2eOxceYEB5r7Y8C3e0ZQ9lw6LZc/lpOS97urYIeYxZfBhgq9HINEMAKX6JwK/vmqHcffaAmrigQt6lAgMBAAECgYEAk1jssTxWMVLSvVm4KJckqGLDvgt5MATdzewv6ZUy6/Ld3/tOETHRy3td0Pon43DFQsqqhRLPixpFqBMP9+pXo0AvjMHXvzWjIvTWQ33Oq9LAiXsDyuZlKIKNflTpNrqWKHCqPMTF0tLWXBOFN4H1Da2LAqX0YAlAx6GOyrqMiLUCQQDhyLvYZEr5vDm9/7Buyez5dNziC88pvEBxGHhR96qIbrqKV1Hn5mXZUKT7I4tq1AL6ZhmzzrJd49h8bYAnkRqbAkEAvycM2fo91/MdSc96I1FCAtuzLVtY8LKZ6e/1cbMkC/ohbMWPDrKche367ZZkDgonQsgm84uDqyqwGZwZdinfvwJBAJgNHevZhuBuERjSI5hIpi1MtSAJweaiX2eKiiTxPEgLAdb+fStF7tsNwz1Zr58uzh6hObfsgBRYx8br2z1KK/cCQBG4EfXmv3LUI2PjKqQ5dRAEw5AxxoXRWHkZ+DuI6o99qTmG+qLXJbE4hSke5rlycX88RHgQ96yXMWm+UZHlgcsCQEq/Rwq85MpTT+624N75TqMHBGnm4yneHz+WDiywg42JOY8tWYF/Q5M7q7FK3oJbgvSN4z8nKPDhkkxbIaEA4Ec=";
	

	
	public void executeRequest(String queryAPi, JSONObject data) {
		String url=null,cipher = null, sign = null;
		url = URL + "/" + queryAPi;
		String param = data.toString();
		StringBuilder builder = new StringBuilder();
		//获取私钥加密后的cipher
		try {
			cipher = CtRsaUtils.encryptByPrivateKey(param, privateKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//获取sign
		try {
			sign = CtRsaUtils.sign(cipher, privateKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//获取url
		try {
			builder.append("cipher=" + URLEncoder.encode(cipher, "UTF-8")).
					append("&accessID=" + URLEncoder.encode(accessID, "UTF-8")).
					append("&sign=" + URLEncoder.encode(sign, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//发起http请求
		logger.info("请求地址：{}" ,url);
		String res = HttpUtils.sendPost(url,builder.toString());
		logger.info("返回值：{}" , res);
	}

	/**
	 * 城市停车大屏-车流量走势对比
	 */
	@Test
	public void GetParkTrend(){
		JSONObject data = new JSONObject();
		JSONObject param = new JSONObject();
		param.accumulate("cityID", "1332");
		param.accumulate("districtID", "110101,110102");
		param.accumulate("streetID", "110101001,11010102");
		param.accumulate("parkID", "123,456");
		param.accumulate("parkingCode", "0108001669");
		param.accumulate("statType", "1");
		data.accumulate("data",param);
		this.executeRequest("visualparkingtrend",data);
	}

	/**
	 * 城市停车大屏-大屏车辆进出分析信息接口
	 */
	@Test
	public void GetParkDetail(){
		JSONObject data = new JSONObject();
		JSONObject param = new JSONObject();
		param.accumulate("cityID", "1332");
		param.accumulate("districtID", "110101,110102");
		param.accumulate("streetID", "110101001,11010102");
		param.accumulate("parkID", "123,456");
		param.accumulate("parkingCode", "0108001669");
		param.accumulate("offset", "0");
		param.accumulate("rows", "10");
		data.accumulate("data",param);
		this.executeRequest("visualparkingdetail",data);
	}

	/**
	 * 城市停车大屏-大屏停车数据概览信息接口
	 */
	@Test
	public void GetParkInfo(){
		JSONObject data = new JSONObject();
		JSONObject param = new JSONObject();
		param.accumulate("cityID", "1332");
		param.accumulate("districtID", "110101,110102");
		param.accumulate("streetID", "110101001,11010102");
		param.accumulate("parkID", "123,456");
		param.accumulate("parkingCode", "0108001669");
		data.accumulate("data",param);
		this.executeRequest("visualparkinginfo",data);
	}

	/**
	 * 城市停车大屏-大屏营收数据明细信息接口
	 */
	@Test
	public void GetParkRevenue (){
		JSONObject data = new JSONObject();
		JSONObject param = new JSONObject();
		param.accumulate("cityID", "1332");
		param.accumulate("districtID", "110101,110102");
		param.accumulate("streetID", "110101001,11010102");
		param.accumulate("parkID", "123,456");
		param.accumulate("parkingCode", "0108001669");
		param.accumulate("offset", "0");
		param.accumulate("rows", "10");
		data.accumulate("data",param);
		this.executeRequest("visualincomedetail",data);
	}

	/**
	 * 城市停车大屏-大屏营收走势对比信息接口
	 */
	@Test
	public void GetParkRevenueTrend (){
		JSONObject data = new JSONObject();
		JSONObject param = new JSONObject();
		param.accumulate("cityID", "1332");
		param.accumulate("districtID", "110101,110102");
		param.accumulate("streetID", "110101001,11010102");
		param.accumulate("parkID", "123,456");
		param.accumulate("parkingCode", "0108001669");
		param.accumulate("statType", "1");
		data.accumulate("data",param);
		this.executeRequest("visualincometrend",data);
	}

	/**
	 * 城市停车大屏-大屏营收数据概览信息接口
	 */
	@Test
	public void GetParkRevenueInfo (){
		JSONObject data = new JSONObject();
		JSONObject param = new JSONObject();
		param.accumulate("cityID", "1101,1201");
		param.accumulate("districtID", "110101,110102");
		param.accumulate("streetID", "110101001,11010102");
		param.accumulate("parkID", "123,456");
		param.accumulate("parkingCode", "0108001669");
		data.accumulate("data",param);
		this.executeRequest("visualincomeinfo",data);
	}

	/**
	 * 城市停车大屏-实时停车资源信息
	 */
	@Test
	public void GetParkInTimeInfo(){
		JSONObject data = new JSONObject();
		JSONObject param = new JSONObject();
		param.accumulate("cityID", "1101,1201");
		param.accumulate("districtID", "110101,110102");
		param.accumulate("streetID", "110101001,11010102");
		param.accumulate("parkID", "123,456");
		param.accumulate("parkingCode", "0108001669");
		data.accumulate("data",param);
		this.executeRequest("visualparkingresource",data);
	}
}
