package cn.com.egova;

import cn.com.egova.base.tools.BeanUtils;
import cn.com.egova.base.tools.DateUtils;
import cn.com.egova.bean.CreatorDTO;
import cn.com.egova.bean.MediaParams;
import cn.com.egova.hzws.common.bean.CommonResult;
import cn.com.egova.hzws.utils.XMLUtils;
import cn.com.egova.utils.HttpClientPoolUtils;
import cn.com.im.basetlibrary.json.JsonUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.xxpt.gateway.shared.client.http.ExecutableClient;
import com.alibaba.xxpt.gateway.shared.client.http.PostClient;
import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
import javafx.beans.binding.ObjectExpression;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import sun.misc.BASE64Encoder;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.FutureTask;

import static java.lang.Thread.*;

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
    public void testFastJsonApi(){
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
        ArrayList<JSONObject> objects = new ArrayList<>();
        for(int i=0;i<3;i++){
            JSONObject objectInArray = new JSONObject();
            objectInArray.put("user","ahan"+i);
            objectInArray.put("age",i);
            objects.add(objectInArray);
        }
        params.put("array",objects);
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
            //未匹配的异常不会catch
        } catch (FormatterClosedException e){
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

    @org.junit.Test
    public  void testEmptyList(){
        List<Integer> list = null;
        HashMap<Integer, Object> map = new HashMap<Integer, Object>();
        list= (List<Integer>)map.get(1);
        System.out.println(list == null );
        System.out.println(list.size() == 0);
    }

    @org.junit.Test
    public  void testStringJoin(){
        List<String> integers = new ArrayList<>();
        ArrayList<String> strings = new ArrayList<>();
        integers.add("1");
        integers.add("2");
        String arr = String.join(",", integers);
        System.out.println(arr);
        System.out.println(String.join(",",strings).length()==0?"null":String.join(",",strings));
    }

    @org.junit.Test
    public  void testAddDateList(){
        String startDate = "2021-03-29";
        String endDateStr = "2021-04-05";
        Date endDate = DateUtils.strToDate(endDateStr);
        List<String> fields = new ArrayList<>();
        String[] fixedFields = new String[]{"监督员标识","监督员姓名","网格","工卡号","通话卡号","流量卡号"};
        fields.addAll(Arrays.asList(fixedFields));

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtils.strToDate(startDate));
        while (!endDate.before(calendar.getTime())){
            fields.add(DateUtils.dateToStr(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_MONTH,1); 
        }
        System.out.println(fields);
    }

    @org.junit.Test
    public  void testDingdingMessage(){
        ExecutableClient executableClient =ExecutableClient.getInstance();
        executableClient.setAccessKey("qianyiceshi-375mUDCn6EHoN3yEn2");
        executableClient.setSecretKey("FI7VPMpy1287o8dw6r87W97321Q48KrCAbIN68g8");
        executableClient.setDomainName("openplatform-pro.ding.zj.gov.cn");
        executableClient.setProtocal("https");
        executableClient.init();

        String api ="/gov/ding/isv/send.json";
        PostClient postClient = executableClient.newPostClient(api);
        CreatorDTO creator = new CreatorDTO("77886370", "", "");
        CreatorDTO reciver = new CreatorDTO("77522062", "", "");
        ArrayList<CreatorDTO> receivers = new ArrayList<>();
        receivers.add(reciver);
        //设置参数
        postClient.addParameter("creator", JSONObject.toJSONString(creator));
        postClient.addParameter("notifyType", "app");
        postClient.addParameter("receivers", JSONObject.toJSONString(receivers));
        postClient.addParameter("tenantId", "196729");
        postClient.addParameter("textType", "plaintext");
        postClient.addParameter("bodyType", "text");
        HashMap<String, String> map = new HashMap<>();
        map.put("text","textzhezhengding");
        postClient.addParameter("body", JSONObject.toJSONString(map));
        //调用API
        String apiResult = postClient.post();
        System.out.println(apiResult);
    }

    @org.junit.Test
    public  void testDateUtils(){
        //caledar的month是0-11
        String dateStr = "2021-02-28";
        String days = "7";
        String hours = "2";
        Date now = new Date();
        //获取时间别用Date.getTime，直接用System
        System.out.println(now+"  "+now.getTime()+" "+System.currentTimeMillis());
        Date date = DateUtils.getDateBeforeOrAfterDays(now, -Integer.valueOf(days));
        Date date2 = DateUtils.getDateBeforeOrAfterHours(now, -2);
        System.out.println("当前时间：" + DateUtils.dateTimeToStr(now));
        System.out.println("前7天：" + DateUtils.dateTimeToStr(date));
        System.out.println("前两小时：" + DateUtils.dateTimeToStr(date2));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtils.strToDate(dateStr));
        calendar.set(Calendar.DAY_OF_MONTH,1);
        System.out.println("给定日期月份第一天：" + DateUtils.dateToStr(calendar.getTime()));
        calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)+1);
        System.out.println("给定日期下个月份第一天：" + DateUtils.dateToStr(calendar.getTime()));
        calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        System.out.println("给定日期月份最后一天：" + DateUtils.dateToStr(calendar.getTime()));
    }

    @org.junit.Test
    public  void testBigDecimal(){
        String value = "12.78";
        BigDecimal bigDecimal = new BigDecimal(value);
        System.out.println(bigDecimal.subtract(new BigDecimal(0)));
    }

    @org.junit.Test
    public  void testMediaUrlEncode(){
        String encodeMediaUrl = "http://10.111.47.249:8080/hdf/subjectFile/%E5%9E%83%E5%9C%BE%E6%B8%A3%E5%9C%9F%E7%9B%91%E7%AE%A1_ljzt_202102/%25E5%259E%2583%25E5%259C%25BE%25E6%25B8%25A3%25E5%259C%259F%25E7%259B%2591%25E7%25AE%25A1_ljzt_202102-ljzt_202102_87.jpg";
        String mediaUrl = "垃圾渣土监管";
        String code = "%E5%9E%83%E5%9C%BE%E6%B8%A3%E5%9C%9F%E7%9B%91%E7%AE%A1";
        Integer index = encodeMediaUrl.lastIndexOf('/');
        if (index >= 0){
            String path = encodeMediaUrl.substring(0, index);
            String fileName = encodeMediaUrl.substring(index+1);
            String codedMediaUrl = "";
            String codeCodeUrl = "";
            try {
                fileName = URLDecoder.decode(fileName, "UTF-8");
                codedMediaUrl = URLEncoder.encode(mediaUrl,"UTF-8");
                codeCodeUrl = URLEncoder.encode(code,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            encodeMediaUrl = path + "/" +fileName;
            System.out.println(codedMediaUrl);
            System.out.println(codeCodeUrl);
        }
    }

    @org.junit.Test
    public  void testBase64() throws UnsupportedEncodingException {
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String chineseStr = URLEncoder.encode("电信公司","UTF-8");
        String encodeUserName = base64Encoder.encode(chineseStr.getBytes("UTF-8"));
        System.out.println(encodeUserName);
        String encodePassWord = base64Encoder.encode("".getBytes());
        System.out.println(encodePassWord);
    }


    @org.junit.Test
    public  void Obj2Map() throws UnsupportedEncodingException {
        CreatorDTO creatorDTO = new CreatorDTO("hello", "test", "afsdfad");
        System.out.println(JSON.toJSON(creatorDTO));
        creatorDTO.setMoney(0.2f);
        Map<String,Object> videoCellInfo = (Map<String,Object>) JSON.toJSON(creatorDTO);
        System.out.println(videoCellInfo);
    }

    @org.junit.Test
    public  void CompareInteger(){
        Integer b = 156;
        int a = 156;
        CreatorDTO creatorDTO = new CreatorDTO();
        creatorDTO.setYear(156);
        System.out.println(creatorDTO.getYear() == b);
        System.out.println(creatorDTO.getYear() == a);
    }

    @org.junit.Test
    public  void StringListContainInteger(){
        String split = "1,2,3,4";
        List<String> actDefIDs = Arrays.asList(split.split(","));
        System.out.println(actDefIDs.contains(String.valueOf(1)));
        System.out.println((int)Double.parseDouble("0.0"));
        HashSet<Integer> integers = new HashSet<>();
//        integers.add(1);
//        integers.add(2);
        ArrayList<Integer> integers1 = new ArrayList<>(integers);
        System.out.println(integers1);
        System.out.println(integers.contains(1));
    }

    @org.junit.Test
    public  void testTypeParse(){
        Long a = 1L;
        int b = a.intValue();
        System.out.println(b);
        int c = 3;
        long d = c;
        //从二进制角度考虑，长转短需要强转将高位置为0，短转长只需要将高位补0
        int e = (int) d;
        System.out.println(d);
    }

    @org.junit.Test
    public  void testDoubleCompareFloat(){
        float a = 123456789000f;
        double b = 123456789;
        long c = 9408000;
        double d=0;
        Object obj = null;
        d = (double) c/(3600*1000f);
        System.out.println(a==(float) b*1000);
        System.out.println((double) c/(3600*1000));
        System.out.println(d);
        System.out.println((String)obj);
        Float workHours = 3.155895f;
        System.out.println(workHours.intValue());
        System.out.println((int)((workHours-workHours.intValue())*60f));
    }

    @org.junit.Test
    public  void testTime() throws InterruptedException {
        Calendar calendar = Calendar.getInstance();
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        LocalTime specfixTime = LocalTime.of(16, 36, 0);
        LocalDate minusDate = date.minusDays(2);
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println("calendar.getTimeInMillis():"+calendar.getTimeInMillis()+",calendar.getTime().getTime():"+calendar.getTime().getTime());
        System.out.println("calendar object:"+calendar);
        System.out.println("LocalDate Object:"+date);
        System.out.println("LocalDate minusDate:"+minusDate);
        System.out.println("LocalTime Object:"+time);
        System.out.println("LocalTime isAfter:"+ (time.isAfter(specfixTime)));
        System.out.println("LocalDateTime:"+ dateTime);
        sleep(10000);
        LocalTime time2 = LocalTime.now();
        Calendar calendar2 = Calendar.getInstance();
        System.out.println(calendar2.getTimeInMillis()-calendar.getTimeInMillis());
    }

    @org.junit.Test
    public  void testBit() throws InterruptedException {
        Integer bitValue = 1335835260;
        System.out.println(bitValue >> 9);
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(1619798400000l);
        System.out.println(DateUtils.dateTimeToStr(instance.getTime()));
    }

    @org.junit.Test
    public  void testStringReplace() {
        StringBuilder builder = new StringBuilder();
        String a = "select sum(case when act_property_id in (1,2,21,22) then 1 else 0 end) as REPORTNODE, sum(case when act_property_id in (3,4,23,24) then 1 else 0 end) as INSTNODE, sum(case when act_property_id in (5,6,25,26) then 1 else 0 end) as DISPATCHNODE, sum(case when act_property_id in (7) then 1 else 0 end) as DISPOSENODE, sum(case when act_property_id in (11,13) then 1 else 0 end) as CHECKNODE, sum(case when act_property_id in (12,14) then 1 else 0 end) as ARCHIVENODE, sum(case when act_property_id in (101) then 1 else 0 end) as ARCHIVED, sum(case when act_property_id in (7,101) then 1 else 0 end) as CSINSTNODE,sum(report_num) as CSREPORTNODE ,sum(postpone_num) AS POSTPONENODE, sum(back_num) AS BACKNODE ,sum(cancel_num) AS CANCELNODE ,sum(overtime_to_dispose_num) AS OVERTIMETODISPOSENUM from to_stat_info where 1 = 1 and create_time > " 
               + "str_to_date('2021-06-17 00:00:00','%Y-%m-%d %H:%i:%S')"
               + " and create_time < str_to_date('2021-06-17 23:59:59','%Y-%m-%d %H:%i:%S') and patrol_deal_flag != 1  and main_type_id not in (80269,80270)";
        builder.append(a);
        String b = builder.toString().replace("sum(case when act_property_id in (5,6,25,26) then 1 else 0 end) as DISPATCHNODE","sum(case when act_property_id in (6) then 1 else 0 end) as DISPATCHNODE");
        builder = new StringBuilder();
        builder.append(b);
        System.out.println(b);
    }
    
}
