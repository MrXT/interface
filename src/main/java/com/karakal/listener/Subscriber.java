package com.karakal.listener;

import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.karakal.constant.SystemConstant;
import com.karakal.controller.meeting.vo.MeetingVO;
import com.karakal.dao.mapper.MeetingUserMapper;
import com.karakal.entity.MeetingUser;
import com.karakal.service.meeting.MeetingService;
import com.karakal.util.LogUtil;

import redis.clients.jedis.JedisPubSub;

@Component
public class Subscriber extends JedisPubSub {

    @Autowired
    private MeetingUserMapper meetingUserMapper;

    @Autowired
    private MeetingService meetingService;

    @Override
    public void onMessage(String channel, String message) {
        LogUtil.DEBUG("Message received. Channel:"+channel+" Msg:"+ message);
        if(SystemConstant.CHANNEL_EXPIRED.equals(channel)){
            String[] arry = message.split("_");
            if(SystemConstant.REDIS_KEY_MEETING.equals(arry[0])){
                String meetingId = arry[1];
                MeetingUser meetingUser = new MeetingUser();
                meetingUser.setType("1");//报名
                meetingUser.setMeetingId(meetingId);
                List<MeetingUser> meetingUsers = meetingUserMapper.select(meetingUser);
                List<String> userIds = new ArrayList<String>();
                for (MeetingUser user : meetingUsers) {
                    userIds.add(user.getUserId());
                }
                if(userIds.size() == 0){
                    return;
                }
                MeetingVO meetingVO = new MeetingVO();
                meetingVO.setMeetingId(meetingId);
                meetingVO.setUserIds(userIds);
                meetingVO.setIsAll(0);
                meetingVO.setType("0");
                meetingService.saveUser(meetingVO);
            } else{
            }
        }
    }

    /** 初始化订阅时候的处理 */
    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        // logger.debug("onSubscribe. Channel:"+channel+" subscribedChannels:"+subscribedChannels);
    }

    /** 取消订阅时候的处理 */
    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        // logger.debug("onUnsubscribe. Channel:"+channel+" subscribedChannels:"+subscribedChannels);
    }

    /** 初始化按表达式的方式订阅时候的处理 */
    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
        // logger.debug("onPSubscribe. pattern:"+pattern+" subscribedChannels:"+subscribedChannels);
    }

    /** 取消按表达式的方式订阅时候的处理 */
    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {
        // logger.debug("onPUnsubscribe. pattern:"+pattern+" subscribedChannels:"+subscribedChannels);
    }

    /** 取得按表达式的方式订阅的消息后的处理 */
    @Override
    public void onPMessage(String pattern, String channel, String message) {
         System.out.println(("onPMessage. pattern:"+pattern+" channel:"+channel+" message:"+message));
    }
}
