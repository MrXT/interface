package com.karakal.service.meeting.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCommands;
import redis.clients.jedis.JedisPool;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.karakal.bean.JPush;
import com.karakal.commons.bean.QueryResult;
import com.karakal.commons.util.MapUtil;
import com.karakal.constant.SystemConstant;
import com.karakal.controller.distribute.vo.DistributeVo;
import com.karakal.controller.meeting.vo.MeetingVO;
import com.karakal.dao.mapper.DistributeMapper;
import com.karakal.dao.mapper.DistributeUserMapper;
import com.karakal.dao.mapper.MeetingMapper;
import com.karakal.dao.mapper.MeetingUserMapper;
import com.karakal.dao.mapper.MeetingroomMapper;
import com.karakal.dao.mapper.MessageMapper;
import com.karakal.dao.mapper.OrderMapper;
import com.karakal.dao.mapper.RemindMapper;
import com.karakal.dao.mapper.ServicetypeMapper;
import com.karakal.dao.mapper.UserMapper;
import com.karakal.entity.Distribute;
import com.karakal.entity.DistributeUser;
import com.karakal.entity.Meeting;
import com.karakal.entity.MeetingUser;
import com.karakal.entity.Message;
import com.karakal.entity.Order;
import com.karakal.entity.Remind;
import com.karakal.entity.Servicetype;
import com.karakal.entity.User;
import com.karakal.exception.BusinessException;
import com.karakal.listener.Subscriber;
import com.karakal.service.meeting.MeetingService;
import com.karakal.util.LogUtil;
import com.karakal.util.UuidUtil;

@Service
public class MeetingServiceImpl implements MeetingService {
    
    @Value("${defaultServicetypeId}")
    private String defaultServicetypeId;
    @Autowired
    private MeetingMapper meetingMapper;
    
    @Autowired
    private OrderMapper orderMapper;
    
    @Resource
    private JPush jpush;
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private RemindMapper remindMapper;

    @Autowired
    private MeetingUserMapper meetingUserMapper;

    @Autowired
    private MeetingroomMapper meetingroomMapper;
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private DistributeMapper distributeMapper;
    
    @Autowired
    private DistributeUserMapper distributeUserMapper;


    @Autowired
    private ServicetypeMapper servicetypeMapper;
    
    @Resource(name = "jedisCluster")
    private JedisCommands jedis;
    
    @Resource(name = "jedisPool")
    private JedisPool jedisPool;
    
    @Autowired
    private Subscriber subscriber;
    
    @PostConstruct
    public void init() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Jedis subscriberJedis = jedisPool.getResource();
                while (true){
                    try {
                        subscriberJedis.subscribe(subscriber, SystemConstant.CHANNEL_EXPIRED);
                    } catch (Exception e) {
                        LogUtil.LOGEXCEPTION(e);
                        subscriber.unsubscribe();
                    }
                }
            }
        }).start();
    }
    @Transactional(rollbackFor = { Exception.class })
    @Override
    public Object save(MeetingVO meeting) {
        int result = 0;
        if (meeting.getMeetingId() == null) {
            DistributeVo distributeVo = new DistributeVo();
            distributeVo.setUserId(meeting.getUserId());
            distributeVo.setType("1");//会议未评价
            if(distributeMapper.queryNoEvaluate(distributeVo).size() >0){
                throw new BusinessException("你还有未评价的会议工单，请完成评价后，在提交申请！");
            }
            MeetingVO vo = new MeetingVO();
            vo.setStartDate(meeting.getStartDate());
            vo.setEndDate(meeting.getEndDate());
            vo.setMeetingroomId(meeting.getMeetingroomId());
            if(meetingMapper.queryByCondition(vo).size() > 0){
                throw new BusinessException("该时间段已有其他会议！");
            }
            meeting.setMeetingId(UuidUtil.get32UUID());
            meeting.setCtime(new Date());
            if(StringUtils.isBlank(meeting.getServicetypeId())){
                meeting.setServicetypeId(defaultServicetypeId);
            }
            result = meetingMapper.insertSelective(meeting);
            int second = (int) ((meeting.getStartDate().getTime()-new Date().getTime())/1000)-10*60;
            if(second>0){
                //加入redis//过期时间为会议开始时间前10分钟、10分钟的时候通知报名的人
                jedis.set(SystemConstant.REDIS_KEY_MEETING+"_"+meeting.getMeetingId(),meeting.getMeetingId().toString());
                jedis.expire(SystemConstant.REDIS_KEY_MEETING+"_"+meeting.getMeetingId(), second);
            }
            Remind remind = new Remind();
            remind.setType("10003");//会议提醒
            List<Remind> reminds = remindMapper.select(remind);
            String userIds [] = new String[reminds.size()];
            int i = 0;
            for (Remind remindvo : reminds) {
                userIds[i++] = remindvo.getUserId();
            }
            User user = userMapper.selectByPrimaryKey(meeting.getUserId());
            String content = user.getName()+"提交了一个会议保障申请，请及时处理。";
            Map<String,Object> map = new HashMap<>();
            map.put("rid", meeting.getMeetingId());
            map.put("type","10003" );
            map.put("content", content);
            List<Message> messages = new ArrayList<Message>();
            if(userIds.length != 0 && jpush.sendMsg(userIds,JSON.toJSONString(map))){
                for (Remind remindvo : reminds) {
                    Message message = new Message();
                    message.setContent(content);
                    message.setRid(meeting.getMeetingId());
                    message.setType(remindvo.getType());
                    message.setUserId(remindvo.getUserId());
                    messages.add(message);
                }
                if(messages.size() != 0){
                    messageMapper.insertList(messages);
                }
            }
        } else {
            result = meetingMapper.updateByPrimaryKeySelective(meeting);
        }
        return result;
    }

    @Override
    public Object query(MeetingVO meeting) {
        if (meeting.getPageNo() != null && meeting.getPageSize() != null) {
            PageHelper.startPage(meeting.getPageNo(), meeting.getPageSize(), "m.ctime desc");
//            PageHelper.startPage(meeting.getPageNo(), meeting.getPageSize(), "m.start_date desc,m.end_date desc");
        }
        List<Map<String, Object>> list = meetingMapper.queryByCondition(meeting);
        if (list.size() >0 && meeting.getIsQueryDetail() == 1) {
            List<String> meetingIds = new ArrayList<String>();
            for (Map<String, Object> map : list) {
                meetingIds.add((String) map.get("meetingId"));
            }
            StringBuilder sb = new StringBuilder();
            for (String s : meetingIds) {
                sb.append("'");
                sb.append(s);
                sb.append("'");
                sb.append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            List<Map<String, Object>> allusers = meetingMapper.queryUsersByIds(sb.toString());
            List<Map<String, Object>> applyUsers = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> signUsers = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> noticeUsers = new ArrayList<Map<String, Object>>();
            for (Map<String, Object> map : allusers) {
                Integer type = Integer.parseInt(map.get("type").toString());
                if (type == 1) {
                    applyUsers.add(map);
                } else if (type == 2) {
                    signUsers.add(map);
                } else {
                    noticeUsers.add(map);
                }
            }
            Map<Object, List<Map<String, Object>>> groupApplyUsers = MapUtil.mapGroupByKey(applyUsers, "meetingId", true);
            Map<Object, List<Map<String, Object>>> groupSignUsers = MapUtil.mapGroupByKey(signUsers, "meetingId", true);
            Map<Object, List<Map<String, Object>>> groupNoticeUsers = MapUtil.mapGroupByKey(noticeUsers, "meetingId", true);
            // 保障人员
            List<Map<String, Object>> handleUsers = meetingMapper.queryHandleUsersByIds(sb.toString());
            Map<Object, List<Map<String, Object>>> groupHandleUsers = MapUtil.mapGroupByKey(handleUsers, "meetingId", true);
            for (Map<String, Object> map : list) {
                map.put("applyUsers", groupApplyUsers.get(map.get("meetingId")));
                map.put("signUsers", groupSignUsers.get(map.get("meetingId")));
                map.put("noticeUsers", groupNoticeUsers.get(map.get("meetingId")));
                map.put("handleUsers", groupHandleUsers.get(map.get("meetingId")));
            }
        }
        return new QueryResult<Map<String, Object>>(new PageInfo<Map<String, Object>>(list));
    }

    @Transactional(rollbackFor = { Exception.class })
    @Override
    public Object delete(MeetingVO meeting) {

        return null;
    }

    @Transactional(rollbackFor = { Exception.class })
    @Override
    public Object saveUser(MeetingVO meeting) {
        Meeting queryMeeting = meetingMapper.selectByPrimaryKey(meeting.getMeetingId());
        if(queryMeeting.getEndDate().getTime()-new Date().getTime()<0){
            throw new BusinessException("会议已过期，不能进行操作！");
        }
        List<MeetingUser> users = new ArrayList<MeetingUser>();
        if(!meeting.getType().equals("0")){
            for (String userId : meeting.getUserIds()) {
                MeetingUser user = new MeetingUser();
                user.setMeetingId(meeting.getMeetingId());
                user.setUserId(userId);
                user.setType(meeting.getType());
                if(meetingUserMapper.select(user).size()>0){
                    if("1".equals(meeting.getType())){
                        throw new BusinessException("你已报名成功，不能重复报名！");
                    }else{
                        throw new BusinessException("你已签到成功，不能重复签到！");
                    }
                }
                users.add(user);
            }
        }
        if (meeting.getType().equals("0")) {
            List<String> userlistIds = new ArrayList<String>();
            if (meeting.getIsAll() == 1) {
                for (User user : userMapper.selectAll()) {
                    if(!"admin".equals(user.getUsername())){
                        userlistIds.add(user.getUserId());
                    }
                }
            } else {
                userlistIds = meeting.getUserIds();
            }
            String[] userIds = new String[userlistIds.size()];
            int i = 0;
            for (String string : userlistIds) {
                userIds[i++] = string;
            }
            List<Message> messages = new ArrayList<Message>();
            
            Map<String,Object> meetingInfo = meetingMapper.queryByCondition(meeting).get(0);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String content = null;
            String type = null;
            if(meeting.getIsAll()==1){
                content = meetingInfo.get("meetingName")+"于"+dateFormat.format(meetingInfo.get("startDate"))+"在"+meetingInfo.get("meetingroomName")+"召开,请大家注意查看内网通知，确定参会人员！";
                type = "10004";//会议报名
            }else{
                content = "你报名的"+meetingInfo.get("meetingName")+"将于"+dateFormat.format(meetingInfo.get("startDate"))+"在"+meetingInfo.get("meetingroomName")+"召开,请准时参加！";
                type= "10007";//会议通知
            }
            Map<String,Object> map = new HashMap<>();
            map.put("rid", meeting.getMeetingId());
            map.put("type",type);
            map.put("content", content);
            if(userIds.length != 0 && jpush.sendMsg(userIds,JSON.toJSONString(map))){
                for (String userId : userIds) {
                    if(meeting.getIsAll() == 0){
                        MeetingUser user = new MeetingUser();
                        user.setMeetingId(meeting.getMeetingId());
                        user.setType(meeting.getType());
                        user.setUserId(userId);
                        users.add(user);
                    }
                    Message message = new Message();
                    message.setContent(content);
                    message.setType(type);
                    message.setUserId(userId);
                    message.setRid(meeting.getMeetingId());
                    messages.add(message);
                    messageMapper.insert(message);
                }
//                if(messages.size() != 0){
//                    messageMapper.insertList(messages);
//                }
            }
        }
        if(!meeting.getType().equals("0") || meeting.getIsAll() == 0){//通知全部不存
            return meetingUserMapper.insertList(users);
        }else{
            return 1; 
        }
    }

    @Override
    public Object queryRecord(MeetingVO meeting) {
        if (meeting.getPageNo() != null && meeting.getPageSize() != null) {
            PageHelper.startPage(meeting.getPageNo(), meeting.getPageSize(), "m.start_date desc,m.end_date desc");
        }
        List<Map<String, Object>> list = meetingMapper.queryRecord(meeting);
        if (list.size() > 0) {
            List<String> meetingIds = new ArrayList<String>();
            for (Map<String, Object> map : list) {
                meetingIds.add((String) map.get("meetingId"));
            }
            StringBuilder sb = new StringBuilder();
            for (String s : meetingIds) {
                sb.append("'");
                sb.append(s);
                sb.append("'");
                sb.append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            List<Map<String, Object>> allusers = meetingMapper.queryUsersByIds(sb.toString());
            List<Map<String, Object>> applyUsers = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> signUsers = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> noticeUsers = new ArrayList<Map<String, Object>>();
            for (Map<String, Object> map : allusers) {
                Integer type = Integer.parseInt(map.get("type").toString());
                if (type == 1) {
                    applyUsers.add(map);
                } else if (type == 2) {
                    signUsers.add(map);
                } else {
                    noticeUsers.add(map);
                }
            }
            Map<Object, List<Map<String, Object>>> groupApplyUsers = MapUtil.mapGroupByKey(applyUsers, "meetingId", true);
            Map<Object, List<Map<String, Object>>> groupSignUsers = MapUtil.mapGroupByKey(signUsers, "meetingId", true);
            Map<Object, List<Map<String, Object>>> groupNoticeUsers = MapUtil.mapGroupByKey(noticeUsers, "meetingId", true);
            for (Map<String, Object> map : list) {
                Date date = new Date();
                Date startDate = (Date) map.get("startDate");
                Date endDate = (Date) map.get("endDate");
                if(date.getTime() < startDate.getTime()){
                    map.put("meetigStatus", -1);//未召开
                }
                if(date.getTime() > endDate.getTime()){
                    map.put("meetigStatus", 1);//已召开
                }
                if(date.getTime() > startDate.getTime() && date.getTime() < endDate.getTime()){
                    map.put("meetigStatus", 0);//进行中
                }
                map.put("applyUsers", groupApplyUsers.get(map.get("meetingId")));
                map.put("signUsers", groupSignUsers.get(map.get("meetingId")));
                map.put("noticeUsers", groupNoticeUsers.get(map.get("meetingId")));
            }
        }
        return new QueryResult<Map<String, Object>>(new PageInfo<Map<String, Object>>(list));
    }

    @Override
    public Object queryMeetingrooms() {
        return meetingroomMapper.selectAll();
    }

    @Override
    public Object queryServicetypes(MeetingVO meeting) {
        if(meeting.getContainMeeting() == 1){
            return servicetypeMapper.selectAll();
        }
        List<Servicetype> servicetypes = servicetypeMapper.selectAll();
        for (Servicetype servicetype : servicetypes) {
            if(servicetype.getServicetypeName().equals("会议")){
                servicetypes.remove(servicetype);
                break;
            }
        }
        return servicetypes;
    }
    @Transactional(rollbackFor = { Exception.class })
    @Override
    public Object updateDistribute(Distribute distribute) {
        Distribute distributeVo = new Distribute();
        distributeVo.setRid(distribute.getRid());
        List<Distribute> distributes = distributeMapper.select(distributeVo);
        if(distributes.size() >0){
            distribute.setDistributeId(distributes.get(0).getDistributeId());
        }else{
            throw new BusinessException("系统找不到该工单，请向管理员确认是否派单！");
        }
        Map<String,Object> map = new HashMap<>();
        Meeting meeting = meetingMapper.selectByPrimaryKey(distribute.getRid());
        Order order = orderMapper.selectByPrimaryKey(distribute.getRid());
        if(meeting == null && order == null){
            throw new BusinessException("系统找不到该工单，请向管理员确认是否派单！");
        }
        User user = userMapper.selectByPrimaryKey(distributes.get(0).getUserId());//保障人员责任人
        User workUser = null;//保障人员
        User applyuser = null;
        if(meeting != null){
            applyuser = userMapper.selectByPrimaryKey(meeting.getUserId());//申请人员
        }else{
            applyuser = userMapper.selectByPrimaryKey(order.getUserId());//申请人员
        }
        String content = null;//推送消息内容
        String type = null;//推送消息类型
        String [] userIds = new String[1];
        if(distribute.getStatus() == 2 &&distribute.getUserId().equals(distributes.get(0).getUserId())){//必须传递userId,确认工单只有责任人才能执行
            if(distributes.get(0).getStatus().intValue() >= 2){
                throw new BusinessException("你已确认工单，不能重复确认！");
            }
            workUser = userMapper.selectByPrimaryKey(distribute.getUserId());//保障人员
            if(meeting != null){
                content = "保障人员"+workUser.getName()+"即将为你"+meeting.getMeetingName()+"提供会议保障服务,电话"+workUser.getPhone()+"。";
                userIds[0] = meeting.getUserId();
                type="10008";//会议确认
            }else{
                content = "保障人员"+workUser.getName()+"即将为你提供工单服务,电话"+workUser.getPhone()+"。";
                userIds[0] = order.getUserId();
                type="10009";//工单确认
            }
        }
        if(distribute.getStatus() == 3&&distribute.getUserId().equals(distributes.get(0).getUserId())){//完成工单只有责任人才能执行
            if(distributes.get(0).getStatus().intValue() >= 3){
                throw new BusinessException("你已完成工单，不能重复点击完成！");
            }
            workUser = userMapper.selectByPrimaryKey(distribute.getUserId());//保障人员
            if(meeting != null){
                content = "保障人员"+user.getName()+"已完成"+meeting.getMeetingName()+"会议保障服务,请及时评价。";
                userIds[0] = meeting.getUserId();
                type="10010";//会议完成
            }else{
                content = "保障人员"+workUser.getName()+"已完成工单服务,请及时评价。";
                userIds[0] = order.getUserId();
                type="10011";//工单完成
            }
           
        }
     // 管理人员收到的是：
//      XXX评价完成。
        if(distribute.getStatus() == 4){
            if(distributes.get(0).getStatus().intValue() >= 4){
                throw new BusinessException("你已评价工单，不能重复评价！");
            }
            content = applyuser.getName()+"评价完成。";
            Remind remind = new Remind();
            if(meeting != null){
                remind.setType("10003");//会议提醒
                type="10012";//会议评价
            }else{
                remind.setType("10002");//工单提醒
                type="10013";//工单评价
            }
            List<Remind> reminds = remindMapper.select(remind);//所有的具有会议管理的管理员
            DistributeUser distributeUser = new DistributeUser();
            distributeUser.setDistributeId(distribute.getDistributeId());
            List<DistributeUser> distributeUsers = distributeUserMapper.select(distributeUser);//所有的保障人员
            userIds = new String[reminds.size()+distributeUsers.size()];
            int i = 0;
            for (DistributeUser du : distributeUsers) {
                userIds[i++] = du.getUserId();
            }
            for (Remind remindvo : reminds) {
                userIds[i++] = remindvo.getUserId();
            }
        }
        map.put("rid", distribute.getRid());
        map.put("type",type);
        map.put("content", content);
        List<Message> messages = new ArrayList<Message>();
        if(userIds.length != 0 && jpush.sendMsg(userIds,JSON.toJSONString(map))){
            for (String userId : userIds) {
                Message message = new Message();
                message.setContent(content);
                message.setType(map.get("type").toString());
                message.setUserId(userId);
                message.setRid(distribute.getRid());
                messages.add(message);
            }
            if(messages.size() != 0){
                messageMapper.insertList(messages);
            }
        }
        if(order != null && distribute.getStatus() == 3){//如果不是会议工单，且状态设置为已完成,//修改评价内容
            order.setHandlerInfo(distribute.getRemark());
            orderMapper.updateByPrimaryKeySelective(order);
        }
        if(distribute.getUserId()!= null && !distribute.getUserId().equals(distributes.get(0).getUserId())){//不是保障人员责任人不能修改工单的状态
            return 1;
        }else{
//            distribute.setRemark(null);//清除该字段
            return distributeMapper.updateByPrimaryKeySelective(distribute);
        }
    }

}
