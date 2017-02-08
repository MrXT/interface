package com.karakal.dao.mapper;

import com.karakal.controller.common.MessageVo;
import com.karakal.entity.Message;
import com.karakal.util.MyMapper;

public interface MessageMapper extends MyMapper<Message> {

    int updateBatchStatus(MessageVo message);
}