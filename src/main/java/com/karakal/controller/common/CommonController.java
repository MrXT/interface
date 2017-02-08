package com.karakal.controller.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import redis.clients.jedis.JedisCommands;

import com.alibaba.fastjson.JSONObject;
import com.karakal.entity.Feedback;
import com.karakal.exception.BusinessException;
import com.karakal.service.system.UserService;
import com.karakal.util.Sms2Util;
import com.karakal.util.StringUtil;

/**
 * 公用控制器 ClassName: ApiController <br/>
 * @author xt(di.xiao@karakal.com.cn)
 * @date 2016年8月3日
 * @version 1.0
 */
@RestController
@RequestMapping("/")
public class CommonController {

    @Autowired
    private UserService userService;
    
    @Resource(name = "jedisCluster")
    private JedisCommands jedis;
    
    @Value("${sms.url}")
    private String smsurl;
    
    @Value("${sms.user}")
    
    private String smsuser;
    
    @Value("${sms.pwd}")
    private String smspwd;
    
    @RequestMapping("getMessages")
    public Object getMessages(MessageVo message) {
        if (StringUtils.isBlank(message.getUserId())) {
            throw new BusinessException("必要参数不能为空！");
        }
        return userService.getMessages(message);
    }
    @RequestMapping("updateMessageStatus")
    public Object updateMessageStatus(MessageVo message) {
        if (message.getStatus()==null ) {
            throw new BusinessException("必要参数不能为空！");
        }
        return userService.updateMessageStatus(message);
    }
    @RequestMapping("updateMyAllMessageStatus")
    public Object updateMyAllMessageStatus(MessageVo message) {
        if (message.getUserId()==null ) {
            throw new BusinessException("必要参数不能为空！");
        }
        return userService.updateMyAllMessageStatus(message);
    }

    @RequestMapping("getCode")
    public Object getCode(String phoneNum, HttpSession session, @RequestParam(defaultValue = "0") int type) {
        if (StringUtils.isBlank(phoneNum)) {
            throw new BusinessException("手机号不能为空！");
        }
        if (type == 0 && userService.queryUser(phoneNum)) {
            throw new BusinessException("该号码已被注册！");
        }
        String code = StringUtil.getRandrom(6);
        session.setAttribute("code", code);
//        String url = StringUtils.isBlank(smsurl)?"http://222.73.117.158/msg/":smsurl;// 应用地址
//        String account = StringUtils.isBlank(smsuser)?"qiyiwl_fayuan":smsuser;// 账号
//        String pswd = StringUtils.isBlank(smspwd)?"Qiyiwl_fayuan":smspwd;// 密码
//        String mobile = phoneNum;// 手机号码，多个号码使用","分割
//        String msg = "亲爱的用户，您的验证码是" + code;// 短信内容
//        boolean needstatus = true;// 是否需要状态报告，需要true，不需要false
//        String product = null;// 产品ID
//        String extno = null;// 扩展码
//        try {
//            String returnString = SmsUtil.batchSend(url, account, pswd, mobile, msg, needstatus, product, extno);
//            System.out.println(returnString);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        Sms2Util.sendMsg(phoneNum, code);
        return code;
    }

    public Boolean SaveFileFromInputStream(InputStream stream, File saveFile) throws IOException {
        FileOutputStream fs = new FileOutputStream(saveFile);
        byte[] buffer = new byte[1024 * 1024];
        int byteread = 0;
        while ((byteread = stream.read(buffer)) != -1) {
            fs.write(buffer, 0, byteread);
            fs.flush();
        }
        fs.close();
        stream.close();
        return true;
    }
    @RequestMapping("queryVersion")
    public Object queryVersion(Integer versionCode,Integer type) {
        if (versionCode==null || type == null) {//type 1:安卓，2:IOS
            throw new BusinessException("必要参数不能为空！");
        }
        return userService.queryVersion(versionCode,type);
    }
    @RequestMapping("getAreas")
    public Object getAreas() {
        return JSONObject.parse(jedis.get("areas") == null ? "[]":jedis.get("areas"));
    }
    /**
     * 插入反馈意见
     * @param feedback
     * @return
     */
    @RequestMapping("insertFeedback")
    public Object insertFeedBack(Feedback feedback) {
        if(StringUtils.isBlank(feedback.getUserId())||StringUtils.isBlank(feedback.getInfo())){
            throw new BusinessException("必要参数不能为空！");
        }
        return userService.saveFeedBack(feedback);
    }
}
