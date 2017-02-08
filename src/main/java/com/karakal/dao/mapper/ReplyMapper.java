package com.karakal.dao.mapper;

import java.util.List;
import java.util.Map;

import com.karakal.controller.bbs.vo.TopicVo;
import com.karakal.entity.Reply;
import com.karakal.util.MyMapper;

public interface ReplyMapper extends MyMapper<Reply> {

    List<Map<String, Object>> selectByContidion(TopicVo topic);
}