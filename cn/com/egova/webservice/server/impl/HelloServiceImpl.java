package cn.com.egova.webservice.server.impl;

import cn.com.egova.webservice.server.HelloService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Random;
import java.util.concurrent.TimeUnit;

//没有serviceName则默认为HelloServiceImplService
//没有portName则默认为HelloServiceImplPort
//没有targetName则默认为http://impl.server.webservice.egova.com.cn/
@WebService(targetNamespace="http://test.elim.com/ws")
public class HelloServiceImpl implements HelloService {
    private Random random = new Random();
    @Override
    public String sayHello(String name) {
//        try {
//            TimeUnit.SECONDS.sleep(5 + random.nextInt(21));
//            //随机睡眠5-25秒
//        }
//        catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return "Hello " + name;
    }
}