package cn.com.egova.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.converters.basic.NullConverter;

import java.util.TimeZone;


/**
 * @author caiqy
 *         2016 11 18
 */
public class XmlUtils {


    private static XStream xStream = new XStream();
    private static String header = "<?xml version='1.0' encoding='UTF-8'?>\n";

    static {
        xStream.autodetectAnnotations(true);//自动识别注解
        xStream.registerConverter(new NullConverter());
        xStream.registerConverter(new DateConverter("yyyy-MM-dd HH:mm:ss", null, TimeZone.getTimeZone("GMT+8")));
    }

    public static String toXml(Object o, String encoding) {
        String tmp = "<?xml version='1.0' encoding='" + encoding + "' ?>\n";
        return tmp.replaceAll("'", "\"") + xStream.toXML(o);
    }

    public static XStream getXStream() {
        XStream xStream = new XStream();
        xStream.autodetectAnnotations(true);
        xStream.registerConverter(new DateConverter("yyyy-MM-dd HH:mm:ss", null, TimeZone.getTimeZone("GMT+8")));
        return xStream;
    }

    public static String toXmlWithoutHeader(Object o) {
        return xStream.toXML(o);
    }

    public static String toXml(Object o) {
        return header.replaceAll("'", "\"") + xStream.toXML(o);
    }

    public static <T> T toObject(String xml) {
        return (T) getXStream().fromXML(xml);
    }

    public static <T> T toObject(String xml, String body, Class<T> type) {
        XStream xs = getXStream();
        xs.alias(body, type);
        return (T) xs.fromXML(xml);
    }

    public static void addAlias(String name, Class clazz) {
        xStream.alias(name, clazz);
    }
}
