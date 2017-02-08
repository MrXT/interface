package com.karakal.dao.mapper;

import java.util.List;
import java.util.Map;

import com.karakal.controller.material.vo.MaterialVo;
import com.karakal.entity.Material;
import com.karakal.util.MyMapper;

public interface MaterialMapper extends MyMapper<Material> {

    List<Map<String, Object>> queryByCondition(MaterialVo material);

    int updateBatchUser(Map<String, Object> map);
}