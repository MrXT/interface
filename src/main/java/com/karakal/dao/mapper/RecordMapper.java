package com.karakal.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.karakal.controller.wallet.vo.AccountVo;
import com.karakal.entity.Record;
import com.karakal.util.MyMapper;

public interface RecordMapper extends MyMapper<Record> {

    List<Record> queryByCondition(AccountVo account);
    /**
     *该时间段内 查询用户用餐次数，
     * @param comsumeTimeslot
     * @return
     */
    List<Record> queryByTimeSlot(Map<String,Object> map);
    Map<String, Object> queryRecord(@Param("recordId")Integer recordId);
}