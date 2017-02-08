package com.karakal.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.karakal.controller.meeting.vo.MeetingVO;
import com.karakal.entity.Meeting;
import com.karakal.util.MyMapper;

public interface MeetingMapper extends MyMapper<Meeting> {

    List<Map<String, Object>> queryByCondition(MeetingVO meeting);

    List<Map<String, Object>> queryUsersByIds(@Param("ids")String ids);

    List<Map<String, Object>> queryHandleUsersByIds(@Param("ids")String join);

    List<Map<String, Object>> queryRecord(MeetingVO meeting);

}