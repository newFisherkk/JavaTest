package cn.com.egova.webservice.server;

import javax.jws.WebService;

//serviceName和portName可不写，但必须要有tagetNameSpace
//没有target会报错 Client received SOAP Fault from server: 找不到{http://server.webservice.egova.com.cn/}sayHello的分派方法
@WebService(targetNamespace="http://test.elim.com/ws")
public interface HelloService {

	String sayHello(String name);

}
