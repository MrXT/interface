package com.karakal.dao.mapper;

import java.util.List;
import java.util.Map;

import com.karakal.controller.distribute.vo.DistributeVo;
import com.karakal.entity.Distribute;
import com.karakal.util.MyMapper;

public interface DistributeMapper extends MyMapper<Distribute> {

    List<Map<String, Object>> queryByCondition(DistributeVo distribute);

    List<Distribute> queryNoEvaluate(DistributeVo distribute);
}