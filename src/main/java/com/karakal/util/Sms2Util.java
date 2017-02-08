
package com.karakal.util;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.karakal.exception.BusinessException;

/** 
 * 说明：短信接口类
 * @version
 */
public class Sms2Util {
    private static String USERNAME = "sc_zjrmfy";//帐号
    private static String PASSWORD = "fy805861";//密码
    private static String spCode = "103909";//企业编号
    private static String content = "您的验证码为";//消息模版
    private static String url = "https://api.ums86.com:9600/sms/Api/Send.do";//地址
    /**
     * 发送短信
     * @param phoneNum 电话号码
     * @param code 验证码
     */
    public static void sendMsg(String phoneNum,String code){
        Map<String, String> params = new HashMap<String, String>();
        params.put("SpCode", spCode);
        params.put("LoginName", USERNAME);
        params.put("Password", PASSWORD);
        try {
            params.put("MessageContent",URLEncoder.encode(content+code,"GBK"));
            params.put("f", "1");
            params.put("UserNumber", phoneNum);
            params.put("SerialNumber", "12345678900987654321");
            StringBuilder sb = new StringBuilder();
            for (String key : params.keySet()) {
                sb.append(key+"="+params.get(key)+"&");
            }
            sb.deleteCharAt(sb.length()-1);
            String response = HttpClientUtil.get(url+"?"+sb.toString());
            LogUtil.DEBUG(response);
        } catch (Exception e) {
            throw new BusinessException("发送短信失败！");
        }
    }
//    public static void main(String[] args) {
//        sendMsg("18200390084", "123456");
//    }
}


