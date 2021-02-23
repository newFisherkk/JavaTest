package cn.com.egova;


import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class UrlEncode {
	public static void main(String[] args) {
		getFileInputStream();
	}
	
	static void urlCode(){
		String url = "https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&tn=baidu&wd=%E8%A7%A3%E5%AF%86%E8%8B%B1%E8%AF%AD&oq=encode&rsv_pq=9a84f19600137f1d&rsv_t=63c0azmUIeQVObakRnvt2qoNAJxuylgB8iOQ095uB1ZIRBFy9kRRf35a7Fk&rqlang=cn&rsv_enter=1&rsv_dl=tb&rsv_btype=t&inputT=4191&rsv_sug3=50&rsv_sug1=43&rsv_sug7=100&rsv_sug2=0&rsv_sug4=4705";
		try {
			String encodeUrl = URLEncoder.encode(url, "UTF-8");
			System.out.println("encodeUrl:"+encodeUrl);
			String decode = URLDecoder.decode(encodeUrl, "UTF-8");
			System.out.println("decodeUrl:"+decode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取文件流
	 */
	static void getFileInputStream(){
		String url = "http://localhost:8080/eUrbanMIS/mediadl/media/getdata/export/1141/export_2020-12-15_163446.xlsx";
		try{
			URL urlCon = new URL(url);
			URLConnection conn = urlCon.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setConnectTimeout(1000);
			conn.setReadTimeout(1000);
			InputStream inStream = conn.getInputStream();
			
		} catch (Exception e){
			System.out.println("出错了"+e.getMessage());
		}
	}
}
