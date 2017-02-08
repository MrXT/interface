
package com.karakal.controller.bbs;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karakal.constant.SystemConstant;
import com.karakal.controller.bbs.vo.TopicVo;
import com.karakal.entity.Reply;
import com.karakal.exception.BusinessException;
import com.karakal.service.bbs.TopicService;

@RestController
@RequestMapping("/topic")
public class TopicController {
    @Resource
    private Map<String, String> statusMap;
    @Autowired
    private TopicService topicService;
    
    @RequestMapping("/query")
    public Object query(TopicVo topic){
        if(StringUtils.isBlank(topic.getUserId())){
            throw new BusinessException(statusMap.get(SystemConstant.NULL_MUSTPARAMETER));
        }
        return topicService.query(topic);
    }
    /**
     * 删除
     * @param topic
     * @return
     */
    @RequestMapping("/delete")
    public Object delete(TopicVo topic){
        if(StringUtils.isBlank(topic.getTopicId()) || StringUtils.isBlank(topic.getUserId())){
            throw new BusinessException(statusMap.get(SystemConstant.NULL_MUSTPARAMETER));
        }
        return topicService.delete(topic);
    }
    /**
     * 点赞
     * @param topic
     * @return
     */
    @RequestMapping("/praise")
    public Object praise(TopicVo topic){
        if(StringUtils.isBlank(topic.getTopicId()) || StringUtils.isBlank(topic.getUserId())){
            throw new BusinessException(statusMap.get(SystemConstant.NULL_MUSTPARAMETER));
        }
        return topicService.praise(topic);
    }
    /**
     * 修改查看数
     * @param topic
     * @return
     */
    @RequestMapping("/scan")
    public Object scan(TopicVo topic){
        if(StringUtils.isBlank(topic.getTopicId()) || StringUtils.isBlank(topic.getUserId())){
            throw new BusinessException(statusMap.get(SystemConstant.NULL_MUSTPARAMETER));
        }
        return topicService.scan(topic);
    }
    @RequestMapping("/save")
    public Object save(TopicVo topic){
        if(StringUtils.isBlank(topic.getTitle()) || StringUtils.isBlank(topic.getUserId())){
            throw new BusinessException(statusMap.get(SystemConstant.NULL_MUSTPARAMETER));
        }
        return topicService.save(topic);
    }
    @RequestMapping("/queryReplys")
    public Object queryReplys(TopicVo topic){
        if(StringUtils.isBlank(topic.getTopicId())){
            throw new BusinessException(statusMap.get(SystemConstant.NULL_MUSTPARAMETER));
        }
        return topicService.queryReplys(topic);
    }
    @RequestMapping("/saveReply")
    public Object saveReply(Reply topic){
        if(StringUtils.isBlank(topic.getTopicId()) || StringUtils.isBlank(topic.getUserId()) || StringUtils.isBlank(topic.getContent())){
            throw new BusinessException(statusMap.get(SystemConstant.NULL_MUSTPARAMETER));
        }
        return topicService.saveReply(topic);
    }
}

