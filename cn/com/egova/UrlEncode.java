package cn.com.egova;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
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
		String strUrl = "http://118.26.0.88:8080/eUrbanGIS-ceshi/home/gis/map/getcellnamebyxy.htm?coordX=116.16981347282874&coordY=39.73566775282287";
		String cellCode = null;
		HttpURLConnection conn = null;
		Document xmlDoc = null;
		InputStream inputStream = null;
		//向gis发送请求获取xml
		try {
			URL servletURL = new URL(strUrl);
			conn = (HttpURLConnection) servletURL.openConnection();

			//通知此连接我们将要发送output并且要接收input
			conn.setConnectTimeout(5*1000);
			conn.setReadTimeout(5*1000);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			//不能使用URL connection的缓存。
			conn.setUseCaches(false);
			conn.setDefaultUseCaches(false);
			conn.setRequestProperty("Content-Type", "application/octet-stream");
			conn.connect();

			DocumentBuilderFactory factory = null;
			DocumentBuilder builder = null;
			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
			inputStream = conn.getInputStream();
			if (null != inputStream) {
				xmlDoc = builder.parse(inputStream);
				//解析xml
				Element elmtRoot = null;
				elmtRoot = xmlDoc.getDocumentElement();
				Text nodeText = null;
				NodeList ndList = null;
				ndList = elmtRoot.getElementsByTagName("CellName");
				if (ndList != null && ndList.getLength() > 0) {
					nodeText = (Text) ndList.item(0).getFirstChild();
					if (nodeText != null) {
						cellCode = nodeText.getNodeValue();
					}
				}
			}
		} catch (Exception e) {
			System.out.println("error"+e.getMessage());
		} finally {
			try {
				if (null != inputStream) {
					inputStream.close();
				}
				if (null != conn) {
					conn.disconnect();
				}
			} catch (Exception e) {
				System.out.println("error"+e.getMessage());
			}

		}
	}
}
