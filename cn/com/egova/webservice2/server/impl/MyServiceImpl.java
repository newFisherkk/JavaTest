package cn.com.egova.webservice2.server.impl;

import cn.com.egova.webservice2.bean.User;
import cn.com.egova.webservice2.server.IMyService;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

//这里指定服务的接口类的路径
@WebService(endpointInterface="cn.com.egova.webservice2.server.IMyService",targetNamespace = "http://ws01.yzl.com/")
public class MyServiceImpl implements IMyService {

    @Override
    public int add(int a, int b) {
        System.out.println("a+b=" + (a+b));
        return a+b;
    }

    @Override
    public boolean login(String username, String password) {
        if("test".equals(username) && "123456".equals(password)){
            System.out.println(username+":login success!!");
            return true;
        }
        System.out.println(username+":login failure!!");
        return false;
    }

    @Override
    public List<User> getChildListByUser(User user) {
        List<User> result = new ArrayList<>();
        result.add(new User("张三", "11111"));
        result.add(new User("李四", "22222"));
        return result;
    }
}