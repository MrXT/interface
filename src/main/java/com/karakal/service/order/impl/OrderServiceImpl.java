
package com.karakal.service.order.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.karakal.bean.JPush;
import com.karakal.commons.bean.QueryResult;
import com.karakal.commons.util.MapUtil;
import com.karakal.controller.distribute.vo.DistributeVo;
import com.karakal.controller.order.vo.OrderVo;
import com.karakal.dao.mapper.DistributeMapper;
import com.karakal.dao.mapper.MeetingMapper;
import com.karakal.dao.mapper.MessageMapper;
import com.karakal.dao.mapper.OrderMapper;
import com.karakal.dao.mapper.RemindMapper;
import com.karakal.dao.mapper.UserMapper;
import com.karakal.entity.Distribute;
import com.karakal.entity.Message;
import com.karakal.entity.Remind;
import com.karakal.entity.User;
import com.karakal.exception.BusinessException;
import com.karakal.service.order.OrderService;
import com.karakal.util.StringUtil;
import com.karakal.util.UuidUtil;

@Service
public class OrderServiceImpl implements OrderService{
    
    @Autowired
    private OrderMapper orderMapper;
    
    @Autowired
    private MeetingMapper meetingMapper;
    
    @Autowired
    private DistributeMapper distributeMapper;
    
    @Autowired
    private RemindMapper remindMapper;
    
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MessageMapper messageMapper;
    
    @Resource
    private JPush jpush;

    @Override
    public Object query(OrderVo order) {
        if (order.getPageNo() != null && order.getPageSize() != null) {
            PageHelper.startPage(order.getPageNo(), order.getPageSize(), "o.ctime desc");
        }
        List<Map<String, Object>> list = orderMapper.queryByCondition(order);
        if (list.size() >0 && order.getIsQueryDetail() == 1) {
            List<String> orderIds = new ArrayList<String>();
            for (Map<String, Object> map : list) {
                orderIds.add((String) map.get("orderId"));
            }
            // 保障人员
            List<Map<String, Object>> handleUsers = meetingMapper.queryHandleUsersByIds(StringUtil.join(orderIds, ","));
            Map<Object, List<Map<String, Object>>> groupHandleUsers = MapUtil.mapGroupByKey(handleUsers, "meetingId", true);
            for (Map<String, Object> map : list) {
                map.put("handleUsers", groupHandleUsers.get(map.get("orderId")));
            }
        }
        return new QueryResult<Map<String, Object>>(new PageInfo<Map<String, Object>>(list));
    }

    @Override
    public Object delete(OrderVo order) {
        Distribute distribute = new Distribute();
        distribute.setRid(order.getOrderId());
        List<Distribute> distributes = distributeMapper.select(distribute);
        if(distributes.size() >0){
            throw new BusinessException("该工单已派单，不能取消！");
        }else{
            return orderMapper.delete(order);
        }
    }

    @Transactional(rollbackFor = { Exception.class })
    @Override
    public Object save(OrderVo order) {
        int result = 0;
        if (order.getOrderId() == null) {
            DistributeVo distributeVo = new DistributeVo();
            distributeVo.setUserId(order.getUserId());
            distributeVo.setType("2");//工单未评价
            if(distributeMapper.queryNoEvaluate(distributeVo).size() >0){
                throw new BusinessException("你还有未评价的工单，请完成评价后，在提交运维服务！");
            }
            order.setOrderId(UuidUtil.get32UUID());
            result = orderMapper.insertSelective(order);
            Remind remind = new Remind();
            remind.setType("10002");//工单提醒
            List<Remind> reminds = remindMapper.select(remind);
            String userIds [] = new String[reminds.size()];
            int i = 0;
            for (Remind remindvo : reminds) {
                userIds[i++] = remindvo.getUserId();
            }
            User user = userMapper.selectByPrimaryKey(order.getUserId());
            String content = user.getName()+"提交了一个工单处理申请，请及时处理。";
            Map<String,Object> map = new HashMap<>();
            map.put("rid", order.getOrderId());
            map.put("type","10002" );
            map.put("content", content);
            List<Message> messages = new ArrayList<Message>();
            if(userIds.length != 0 && jpush.sendMsg(userIds,JSON.toJSONString(map))){
                for (Remind remindvo : reminds) {
                    Message message = new Message();
                    message.setContent(content);
                    message.setRid(order.getOrderId());
                    message.setType(remindvo.getType());
                    message.setUserId(remindvo.getUserId());
                    messages.add(message);
                }
                if(messages.size() != 0){
                    messageMapper.insertList(messages);
                }
            }
        } else {
            result = orderMapper.updateByPrimaryKeySelective(order);
        }
        return result;
    }
}

