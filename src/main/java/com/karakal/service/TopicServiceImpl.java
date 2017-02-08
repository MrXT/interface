
package com.karakal.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.karakal.commons.bean.QueryResult;
import com.karakal.controller.bbs.vo.TopicVo;
import com.karakal.dao.mapper.ReplyMapper;
import com.karakal.dao.mapper.TopicMapper;
import com.karakal.dao.mapper.TopicUserMapper;
import com.karakal.entity.Reply;
import com.karakal.entity.Topic;
import com.karakal.entity.TopicUser;
import com.karakal.exception.BusinessException;
import com.karakal.service.bbs.TopicService;
import com.karakal.util.DateUtil;
import com.karakal.util.UuidUtil;

@Service
public class TopicServiceImpl implements TopicService{
    
    @Autowired
    private TopicMapper topicMapper;
    
    @Autowired
    private ReplyMapper replyMapper;
    
    @Autowired
    private TopicUserMapper topicUserMapper;
    @Transactional(rollbackFor = { Exception.class })
    @Override
    public Object query(TopicVo topic) {
        String userId = topic.getUserId();
//        Boolean isPraise = false;//是否点赞
        if(StringUtils.isNotBlank(topic.getTopicId())){//如果查询单个的话就查看量自动加一
            scan(topic);
            /*if(StringUtils.isNotBlank(topic.getUserId())){
                TopicUser topicUser = new TopicUser();
                topicUser.setTopicId(topic.getTopicId());
                topicUser.setUserId(topic.getUserId());
                if(topicUserMapper.select(topicUser).size()>0){
                    isPraise = true;
                }
            }*/
        }
        if (topic.getPageNo() != null && topic.getPageSize() != null) {
            PageHelper.startPage(topic.getPageNo(), topic.getPageSize(), "t.ctime desc");
        }
        if("1".equals(topic.getType()) || StringUtils.isNotBlank(topic.getTopicId())){
            topic.setUserId(null);
        }
        List<Map<String, Object>> list = topicMapper.selectByContidion(topic);
        TopicUser arg0 = new TopicUser();
        arg0.setType(0);//点赞
        arg0.setUserId(userId);//当前用户ID
        List<TopicUser> topicUsers = topicUserMapper.select(arg0);
        for (Map<String, Object> map : list) {
            Boolean isPraise = false;
            for (TopicUser topicUser : topicUsers) {
                if(map.get("topicId").toString().equals(topicUser.getTopicId())){
                    isPraise = true;
                    break;
                }
            }
            map.put("isPraise",isPraise);
            if(map.get("pics") != null){
                map.put("pics", map.get("pics").toString().split(","));
            }
        }
        return new QueryResult<Map<String, Object>>(new PageInfo<Map<String, Object>>(list));
    }
    @Transactional(rollbackFor = { Exception.class })
    @Override
    public Object save(TopicVo topic) {
        int result = 0;
        if(topic.getTopicId() == null){
            topic.setTopicId(UuidUtil.get32UUID());
            topic.setCtime(DateUtil.date2String(new Date(), "yyyy-MM-dd HH:mm"));
            result = topicMapper.insertSelective(topic);
        }else{
            result = topicMapper.updateByPrimaryKeySelective(topic);
        }
        return result;
    }

    @Override
    public Object queryReplys(TopicVo topic) {
        if (topic.getPageNo() != null && topic.getPageSize() != null) {
            PageHelper.startPage(topic.getPageNo(), topic.getPageSize(), "r.ctime desc");
        }
        List<Map<String, Object>> list = replyMapper.selectByContidion(topic);
        
        return new QueryResult<Map<String, Object>>(new PageInfo<Map<String, Object>>(list));
    }
    @Transactional(rollbackFor = { Exception.class })
    @Override
    public Object saveReply(Reply reply) {
        reply.setReplyId(UuidUtil.get32UUID());
        reply.setCtime(DateUtil.date2String(new Date(), "yyyy-MM-dd HH:mm"));
        return replyMapper.insertSelective(reply);
    }
    @Transactional(rollbackFor = { Exception.class })
    @Override
    public Object delete(TopicVo topic) {
        List<Map<String, Object>> list = topicMapper.selectByContidion(topic);
        if(list.size()>0){
            return topicMapper.deleteByPrimaryKey(topic.getTopicId());
        }else{
            throw new BusinessException("删除失败，帖子不存在！");
        }
    }
    @Transactional(rollbackFor = { Exception.class })
    @Override
    public Object praise(TopicVo topic) {
        Topic topicEntity = topicMapper.selectByPrimaryKey(topic.getTopicId());
        if(topicEntity != null){
            TopicUser topicUser = new TopicUser();
            topicUser.setTopicId(topic.getTopicId());
            topicUser.setUserId(topic.getUserId());
            if(topicUserMapper.select(topicUser).size()>0){
                throw new BusinessException("不能重复点赞！");
            }else{
                if(topicUserMapper.insertSelective(topicUser)>0){
                    topicEntity.setPraiseCount(topicEntity.getPraiseCount()+1);
                    return topicMapper.updateByPrimaryKeySelective(topicEntity);
                }else{
                    throw new BusinessException("点赞失败！");
                }
            }
        }else{
            throw new BusinessException("该帖子不存在！");
        }
    }
    @Transactional(rollbackFor = { Exception.class })
    @Override
    public Object scan(TopicVo topic) {
        Topic topicEntity = topicMapper.selectByPrimaryKey(topic.getTopicId());
        /*if(topicEntity != null){
            TopicUser topicUser = new TopicUser();
            topicUser.setTopicId(topic.getTopicId());
            topicUser.setUserId(topic.getUserId());
            topicUser.setType(1);//查看
            if(topicUserMapper.select(topicUser).size()>0){
                throw new BusinessException("已查看！");
            }else{
                if(topicUserMapper.insertSelective(topicUser)>0){
                    topic.setScanCount(topicEntity.getScanCount()+1);
                    return topicMapper.updateByPrimaryKeySelective(topic);
                }else{
                    throw new BusinessException("查看失败！");
                }
            }
        }else{
            throw new BusinessException("该帖子不存在！");
        }*/
        topicEntity.setScanCount(topicEntity.getScanCount()+1);
        return topicMapper.updateByPrimaryKeySelective(topicEntity);
    }

}

