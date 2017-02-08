
package com.karakal.service.distribute.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.karakal.commons.bean.QueryResult;
import com.karakal.controller.distribute.vo.DistributeVo;
import com.karakal.dao.mapper.DistributeMapper;
import com.karakal.entity.Distribute;
import com.karakal.service.distribute.DistributeService;

@Service
public class DistributeServiceImpl implements DistributeService{
    
    @Autowired
    private DistributeMapper distributeMapper;

    @Override
    public Object query(DistributeVo distribute) {
        if (distribute.getPageNo() != null && distribute.getPageSize() != null) {
            PageHelper.startPage(distribute.getPageNo(), distribute.getPageSize(), "d.date desc");
        }
        List<Map<String, Object>> list = distributeMapper.queryByCondition(distribute);
        return new QueryResult<Map<String, Object>>(new PageInfo<Map<String, Object>>(list));
    }

    @Override
    public List<Distribute>  queryNoEvaluate(DistributeVo distribute) {
        return distributeMapper.queryNoEvaluate(distribute);
    }

}

