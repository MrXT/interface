package com.karakal.dao.mapper;

import java.util.List;
import java.util.Map;

import com.karakal.controller.material.vo.MaterialVo;
import com.karakal.entity.MaterialOrder;
import com.karakal.util.MyMapper;

public interface MaterialOrderMapper extends MyMapper<MaterialOrder> {

    List<Map<String, Object>> queryByCondition(MaterialVo material);
}