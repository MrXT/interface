
package com.karakal.service.bbs;

import com.karakal.controller.bbs.vo.TopicVo;
import com.karakal.entity.Reply;


public interface TopicService {

    Object query(TopicVo topic);

    Object save(TopicVo topic);

    Object queryReplys(TopicVo topic);

    Object saveReply(Reply topic);

    Object delete(TopicVo topic);

    Object praise(TopicVo topic);

    Object scan(TopicVo topic);

}

