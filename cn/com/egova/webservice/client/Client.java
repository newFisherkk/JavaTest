package cn.com.egova.webservice.client;


import cn.com.egova.webservice.server.HelloService;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import java.net.URL;
import java.util.Map;

/**
 * 测试连接webService超时
 */
public class Client {
	public static void main(String[] args) throws Exception {
		String targetNamespace = "http://test.elim.com/ws";
		QName serviceName = new QName(targetNamespace, "HelloServiceImplService");
		QName portName = new QName(targetNamespace, "HelloServiceImplPort");
		URL wsdl = new URL("http://localhost:8888/hello");
		//内部会创建一个ServiceDelegate类型的对象赋给属性delegate
		Service service = Service.create(wsdl, serviceName);
		//会利用delegate创建一个服务接口的代理对象，同时还会代理BindingProvider和Closeable接口。
		HelloService helloService = service.getPort(portName, HelloService.class);


		BindingProvider bindingProvider = (BindingProvider) helloService;
		Map<String, Object> requestContext = bindingProvider.getRequestContext();
		requestContext.put("com.sun.xml.internal.ws.connection.timeout", 10 * 1000);//建立连接的超时时间为10秒
		requestContext.put("com.sun.xml.internal.ws.request.timeout", 15 * 1000);//指定请求的响应超时时间为15秒

		//在调用接口方法时，内部会发起一个HTTP请求，发起HTTP请求时会从BindingProvider的getRequestContext()返回结果中获取超时参数，
		//分别对应com.sun.xml.internal.ws.connection.timeout和com.sun.xml.internal.ws.request.timeout参数，
		//前者是建立连接的超时时间，后者是获取请求响应的超时时间，单位是毫秒。如果没有指定对应的超时时间或者指定的超时时间为0都表示永不超时。

		System.out.println(helloService.sayHello("Elim"));
	}
}
