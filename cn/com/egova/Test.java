package cn.com.egova;

import cn.com.egova.bean.MediaParams;
import cn.com.egova.hzws.common.bean.CommonResult;
import cn.com.egova.hzws.utils.XMLUtils;
import cn.com.egova.utils.HttpClientPoolUtils;
import cn.com.im.basetlibrary.json.JsonUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.*;

public class Test {
    
    public static void main(String[] args) {

    }

    @org.junit.Test
    public void testIntegerEqualString(){
        Integer i=-1;
        System.out.println(i.equals(Integer.valueOf("-1")));
        System.out.println("-1".equals(i));
        String s = "  ";
        System.out.println(null == s);
        System.out.println(s.isEmpty());
        System.out.println(s.length());
        System.out.println(s.trim().length());
        System.out.println(s.trim().isEmpty());
    }

    @org.junit.Test
    public void testStrParseLong(){
        String recStr="-4401001099128";
        long aLong = Long.parseLong(recStr);
        System.out.println(aLong);
    }
    
    
    @org.junit.Test
    public void testUrl(){
        String urlStr = "http://10.134.16.211:8086theResult";
        URL url = null;
        try {
             url=new URL(urlStr);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    @org.junit.Test
    public void testObjToXml(){
        //需要  AXMLPrinter-2.0  和    xstream-1.4.7 2个jar包
        String res = "";
        CommonResult result = new CommonResult(-1, "已被别人办理", "");
        res = XMLUtils.restoreSPChar(XMLUtils.toXML(result, "UTF-8"));
        System.out.println(res);
    }
    
    @org.junit.Test
    public void testMapNullStr(){
        HashMap<String, String> strMap = new HashMap<>(8);
        strMap.put("one","");
        System.out.println(strMap.get("one").trim());
    }

    @org.junit.Test
    public void testBaseType(){
        double a = 0.0d;
        System.out.println(String.valueOf(a).equals("0.0"));
    }
    
    @org.junit.Test
    public void testJsonUtil(){
        Map<String, Object> map = new HashMap<>(7);
        map.put("clientId",1);
        map.put("redirectUri",2);
        map.put("state",3);
        map.put("responseType","3");
        map.put("scope",4);
        map.put("personToken",5);
        map.put("sign","SIGN");
        System.out.println("mapStr:"+map.toString());
        System.out.println("jsonArray:"+JSONArray.toJSON(map).toString());
        System.out.println("JSON:"+JSON.toJSONString(map));
        JSONObject params = new JSONObject();
        params.put("unitID",1);
        params.put("unitName",2);
        params.put("humanName",3);
        params.put("humanCode","4");
        params.put("patrolFlag",0);
        params.put("userName",1);
        params.put("password","");
        System.out.println("JSONObject:"+params.toJSONString());
    }
    
    @org.junit.Test
    public void testByteToString() throws UnsupportedEncodingException {
        String a = "你好啊";
        byte[] utfBytes = a.getBytes("UTF-8");
        byte[] gbkBytes = a.getBytes("GBK");
        String sutf = new String(utfBytes,"UTF-8");
        String sgbk = new String(gbkBytes,"GBK");
        String futf = new String(utfBytes,"GBK");
        String fgbk = new String(gbkBytes,"UTF-8");
        System.out.println(sutf);
        System.out.println(sgbk);
        System.out.println(futf);
        System.out.println(fgbk);
    }
    
    @org.junit.Test
    public void testEqual()  {
        String s = "";
        System.out.println(s==null || s.length()==0);
        ArrayList<Object> objects = new ArrayList<>();
        for(Object a : objects){
            System.out.println("sss");
        }
        System.out.println(Arrays.asList("ss"));
    }
    
    @org.junit.Test
    public void tryCatch()  {
        try{
            tryCatch2();
        } catch (Exception e){
            System.out.println("出错了"+e.getMessage());
        }
    }
    
    @org.junit.Test
    public void tryCatch2() {
        try{
            Object s = "ss";
            Integer i =  (Integer) s;
        } catch (Exception e){
            System.out.println("e.messgae:"+e.getMessage()+",e.cause:"+e.getCause()+",e:"+e);
            try {
                throw new Exception("出错了");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    @org.junit.Test
    public void testFloat(){
        String hour ="5";
        Float aFloat = Float.valueOf(hour);
        System.out.println(aFloat/8);
    }

    @org.junit.Test
    public void testStrArrToHashMap(){
        String str ="[{\"mediaSize\":\"734733\",\"msgType\":\"1\",\"cardID\":\"100676\",\"msgID\":\"1\",\"mediaType\":\"0\",\"dealType\":\"-1\",\"taskID\":\"0\",\"mediaName\":\"egova_8_20200622094708472.jpg\",\"order\":\"1\",\"mediaUsage\":\"上报\"},{\"mediaSize\":\"146198\",\"msgType\":\"1\",\"cardID\":\"100676\",\"msgID\":\"1\",\"mediaType\":\"0\",\"dealType\":\"-1\",\"taskID\":\"0\",\"mediaName\":\"egova_7579_20200710100046674.jpg\",\"order\":\"2\",\"mediaUsage\":\"上报\"}]";
        ArrayList<HashMap<String,String>> list = JsonUtil.getObject(str, ArrayList.class);//这个实际得到得是linkedTreeMap
        List<MediaParams> params = JsonUtil.getList(str,  new TypeToken<List<MediaParams>>(){}.getType());
        List<HashMap<String,String>> maps = JsonUtil.getList(str,  new TypeToken<List<HashMap>>(){}.getType()); //这个才能得到真正的hashMap
        System.out.println(list.get(0));
        System.out.println(params.get(0));
        System.out.println(maps.get(0));
    }
    
    @org.junit.Test
    public  void testHashMapInitialSize(){
        HashMap<String, Integer> map = Maps.newHashMapWithExpectedSize(2);
        map.put("1",1);
        map.put("2",2);
    }
    
    @org.junit.Test
    public  void testHttpPost(){
        Map<String, String> map = Maps.newHashMapWithExpectedSize(2);
        map.put("client_id","bkvitoClientId");
        map.put("client_secret","bkvitoSecret");
        String data = HttpClientPoolUtils.sendPostWithFile("http://222.85.137.77:3366/api/vito-auth/vito/auth/token",null,map,null);
        JSONObject accessTokenInfo = JSON.parseObject(data);
        Map<String, String> headParam = Maps.newHashMapWithExpectedSize(1);
        Map<String, String> param = Maps.newHashMapWithExpectedSize(2);
        param.put("access_token",accessTokenInfo.getString("data"));
        param.put("code","BkVito");
        headParam.put("Authorization","bearer 11919913-8514-4f6b-b05b-4a3e15ce5a0f");
        String humanInfo = HttpClientPoolUtils.sendPostWithFile("http://222.85.137.77:3366/api/vito-auth/vito/auth/getUserInfo",null,param,headParam);
        System.out.println(humanInfo);
    }
    
    @org.junit.Test
    public  void randomVerifyCode(){
        Random random = new Random();
        System.out.println(random.nextInt(10));
        String verifyCode = String.valueOf(100000 + random.nextInt(900000));
        System.out.println(verifyCode);
    }
}
