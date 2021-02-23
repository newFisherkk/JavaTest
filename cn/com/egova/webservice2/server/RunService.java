package cn.com.egova.webservice2.server;

import cn.com.egova.webservice2.server.impl.MyServiceImpl;

import javax.xml.ws.Endpoint;

public class RunService {
    
    public static void main(String[] args) {
        String url = "http://localhost:8888/ws01";
        Endpoint.publish(url, new MyServiceImpl());
    }
}