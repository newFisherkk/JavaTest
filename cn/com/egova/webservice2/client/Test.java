package cn.com.egova.webservice2.client;

import cn.com.egova.webservice2.server.IMyService;
import com.github.davidmoten.guavamini.annotations.VisibleForTesting;
import org.w3c.dom.Document;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;

/**
 * 
 * helloword项目:测试环境跟服务器环境在同一机器同一项目内使用,服务在其他机器上则在后续介绍
 * 
 * @author Administrator
 *
 */

public class Test {

    public static void main(String[] args) throws Exception {
        test();
        System.out.println("\r\n");
        test1();
        System.out.println("\r\n");
        test2();
    }

    /**
     * 本地访问本地服务
     */
    public static void test() throws MalformedURLException {

        String namespaceUrl = "http://ws01.yzl.com/";

        URL url = new URL("http://localhost:8888/ws01?wsdl");

        //使用namespaceURL和服务名创建QName
        //<definitions ... targetNamespace="http://ws01.yzl.com/" name="MyServiceImplService">
        //<service name="MyServiceImplService">
        QName qname = new QName(namespaceUrl,"MyServiceImplService");

        //创建服务
        Service service = Service.create(url, qname);

        //服务器和客户端在同一机器时可以直接用服务的接口类,不在同一机器的具体用法见后面的章节
        IMyService client = service.getPort(IMyService.class);

        System.out.println(client.add(10, 20));
        System.out.println(client.login("test", "123456"));
        System.out.println(client.login("test", "111111"));
    }
    
    /**
     * 创建访问add方法的SOAP消息的xml
     */
    
    public static void test1(){
        try {
            //1、创建消息工厂
            MessageFactory factory = MessageFactory.newInstance();
            //2、根据消息工厂创建SoapMessage
            SOAPMessage message = factory.createMessage();
            //3、创建SOAPPart
            SOAPPart part = message.getSOAPPart();
            //4、获取SOAPENvelope
            SOAPEnvelope envelope = part.getEnvelope();
            //5、可以通过SoapEnvelope有效的获取相应的Body和Header等信息
            SOAPBody body = envelope.getBody();
            //6、根据Qname创建相应的节点(QName就是一个带有命名空间的)
            QName qname = new QName("http://ws01.yzl.com",
                    "add","ns");//<ns:add xmlns:ns=http://ws01.yzl.com>这里指定ns是前缀，必须指定，随便定义即可，不定义这消息将无效
            //如果使用以下方式进行设置，会见<>转换为<和&gt
            //body.addBodyElement(qname).setValue("<a>1</a><b>2</b>");
            SOAPBodyElement ele = body.addBodyElement(qname);
            ele.addChildElement("a").setValue("22");
            ele.addChildElement("b").setValue("33");
            //打印消息信息
            message.writeTo(System.out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送add方法的SOAP的消息并接受解析返回的soap消息（使用Service.Mode.MESSAGE模式进行发送数据）
     <service name="MyServiceImplService">
     <port name="MyServiceImplPort" binding="tns:MyServiceImplPortBinding">
     <soap:address location="http://localhost:8888/ws01" /> 
     </port>
     </service>
     *
     */
    public static void test2(){
        try {
            String namespace = "http://ws01.yzl.com/";
            String wsdlUrl = "http://localhost:8888/ws01?wsdl";

            //1、创建服务(Service)
            URL url = new URL(wsdlUrl);
            QName qname = new QName(namespace,"MyServiceImplService");
            Service service = Service.create(url, qname);

            //2、创建Dispatch
            //public interface Dispatch<T>extends BindingProviderDispatch 接口提供对动态调用服务端点操作的支持。javax.xml.ws.Service 接口作为创建 Dispatch 实例的工厂。 
            Dispatch<SOAPMessage> dispatch = service.createDispatch(new QName(namespace,"MyServiceImplPort"), SOAPMessage.class, Service.Mode.MESSAGE);

            //3、创建SOAPMessage
            MessageFactory factory = MessageFactory.newInstance();
            SOAPMessage message = factory.createMessage();
            SOAPPart part = message.getSOAPPart();
            SOAPEnvelope envelope = part.getEnvelope();
            SOAPBody body = envelope.getBody();
            //接口方法前要加 @WebMethod(operationName = "add") 不然找不到服务
            QName portQname = new QName("http://ws01.yzl.com/",
                    "add","ns");
            SOAPBodyElement ele = body.addBodyElement(portQname);
            ele.addChildElement("a").setValue("22");
            ele.addChildElement("b").setValue("33");

            //4、通过Dispatch传递消息,并返回响应消息
            SOAPMessage returnMessage = dispatch.invoke(message);
            returnMessage.writeTo(System.out);//打印返回消息
            System.out.println();

            //5、解析返回的SOAP消息的XML
            Document doc = returnMessage.getSOAPBody().extractContentAsDocument();
            //Document doc = returnMessage.getSOAPPart().getEnvelope().getBody().extractContentAsDocument();
            String result = doc.getElementsByTagName("addResult").item(0).getTextContent();
            System.out.println("result is :" + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}