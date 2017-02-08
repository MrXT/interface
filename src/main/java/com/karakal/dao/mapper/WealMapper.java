package com.karakal.dao.mapper;

import java.util.List;
import java.util.Map;

import com.karakal.controller.weal.vo.WealVO;
import com.karakal.entity.Weal;
import com.karakal.util.MyMapper;

public interface WealMapper extends MyMapper<Weal> {

    List<Map<String, Object>> queryByCondition(WealVO weal);

    List<Map<String, Object>> queryWeals(WealVO weal);

    List<Map<String, Object>> queryIsNew(WealVO weal);

}