package cn.com.egova.webservice.server;

import cn.com.egova.webservice.server.impl.HelloServiceImpl;

import javax.xml.ws.Endpoint;

public class Server {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8888/hello", new HelloServiceImpl());
    }
}