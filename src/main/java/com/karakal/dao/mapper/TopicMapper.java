package com.karakal.dao.mapper;

import java.util.List;
import java.util.Map;

import com.karakal.controller.bbs.vo.TopicVo;
import com.karakal.entity.Topic;
import com.karakal.util.MyMapper;

public interface TopicMapper extends MyMapper<Topic> {

    List<Map<String, Object>> selectByContidion(TopicVo topic);
}