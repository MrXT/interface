package com.karakal.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.karakal.dao.mapper.UserMapper;
import com.karakal.util.LogUtil;
import com.karakal.util.StringUtil;

@Component
public class JPush {

    @Value("${masterSecret}")
    private String masterSecret;

    @Value("${appKey}")
    private String appKey;
    
    @Value("${prodEvn}")
    private Boolean prodEvn;
    @Autowired
    private UserMapper userMapper;

    @SuppressWarnings("deprecation")
    public Boolean sendMsg(String alias[], String message) {
        for (String string : alias) {
            if (string == null) {
                return false;
            }
        }
        JSONObject object = JSON.parseObject(message);
        Map<String, String> map = new HashMap<>();
        for (String key : object.keySet()) {
            map.put(key, object.getString(key));
        }
        try {
            List<String> userIds = new ArrayList<String>();
            for (String string : alias) {
                userIds.add(string);
            }
            List<String> quietUserIds = userMapper.queryQuiet(StringUtil.join(userIds, ","));
            JPushClient jpushClient = new JPushClient(masterSecret, appKey, 3);
            // PushPayload payload =
            // PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.alias(alias))
            // .setNotification(Notification.alert(message)).build();
            userIds.removeAll(quietUserIds);
            if(userIds.size() != 0){
                PushPayload payload = PushPayload
                    .newBuilder()
                    .setPlatform(Platform.android_ios())
                    .setAudience(Audience.alias(userIds))
                    .setNotification(
                        Notification.newBuilder()
                            .addPlatformNotification(AndroidNotification.newBuilder().addExtras(map).setBuilderId(1).setAlert(object.getString("content")).build())
                            .addPlatformNotification(IosNotification.newBuilder().setAlert(object.getString("content")).setBadge(5).addExtras(map).build()).build())
                    .setOptions(Options.newBuilder().setApnsProduction(prodEvn).build()).setMessage(Message.content(message)).build();
                PushResult result1 = jpushClient.sendPush(payload);
                if (result1.getResponseCode() == 200) {
                } else {
                    LogUtil.ERROR(result1.getOriginalContent());
                    return false;
                }
            }
            if(quietUserIds.size() !=0){
                PushPayload payload1 = PushPayload
                    .newBuilder()
                    .setPlatform(Platform.android_ios())
                    .setAudience(Audience.alias(quietUserIds))
                    .setNotification(
                        Notification.newBuilder()
                            .addPlatformNotification(AndroidNotification.newBuilder().addExtras(map).setBuilderId(1).setAlert(object.getString("content")).build())
                            .addPlatformNotification(IosNotification.newBuilder().setAlert(object.getString("content")).setBadge(5).disableSound().addExtras(map).build()).build())
                    .setOptions(Options.newBuilder().setApnsProduction(prodEvn).build()).setMessage(Message.content(message)).build();
                PushResult result1 = jpushClient.sendPush(payload1);
                if (result1.getResponseCode() == 200) {
                } else {
                    LogUtil.ERROR(result1.getOriginalContent());
                    return false;
                }
            }
        } catch (APIConnectionException | APIRequestException e) {
            LogUtil.LOGEXCEPTION(e);
            return false;
        }
        return true;
    }

}
