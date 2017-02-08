package com.karakal.service.weal.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.karakal.commons.bean.QueryResult;
import com.karakal.commons.util.MapUtil;
import com.karakal.controller.weal.vo.WealVO;
import com.karakal.dao.mapper.WealMapper;
import com.karakal.dao.mapper.WealUserMapper;
import com.karakal.entity.WealUser;
import com.karakal.exception.BusinessException;
import com.karakal.service.weal.WealService;
import com.karakal.util.StringUtil;

@Service
public class WealServiceImpl implements WealService {

    @Autowired
    private WealMapper wealMapper;

    @Autowired
    private WealUserMapper wealUserMapper;

    @Override
    public Object saveUser(WealVO weal) {
        WealUser wealUser = new WealUser();
        wealUser.setWealId(weal.getWealId());
        wealUser.setUserId(weal.getUserId());
        wealUser.setType(weal.getType());
        if (wealUserMapper.select(wealUser).size() > 0) {
            if (weal.getType() == 2) {
                throw new BusinessException("你已报名成功，不能重复报名！");
            } else {
                return 1;// 如果type=1，就直接返回
            }
        }
        return wealUserMapper.insertSelective(wealUser);
    }

    @Override
    public Object query(WealVO weal) {
        if (weal.getPageNo() != null && weal.getPageSize() != null) {
            PageHelper.startPage(weal.getPageNo(), weal.getPageSize(), "w.ctime desc");
        }
        List<Map<String, Object>> list = null;
        if (weal.getType() == 1) {// 查询所有的福利列表
            list = wealMapper.queryByCondition(weal);
        } else {// 查询用户的福利列表
            list = wealMapper.queryWeals(weal);
        }
        if (list.size() > 0) {
            List<String> wealIds = new ArrayList<String>();
            for (Map<String, Object> map : list) {
                wealIds.add((String) map.get("wealId"));
            }
            weal.setWealId(StringUtil.join(wealIds, ","));
            List<Map<String, Object>> scanList = wealMapper.queryIsNew(weal);
            Map<Object, List<Map<String, Object>>> groupList = MapUtil.mapGroupByKey(scanList, "wealId", true);
            for (Map<String, Object> map : list) {
                Boolean isNew = true;
                Boolean isApply = false;
                List<Map<String, Object>> maplist = groupList.get(map.get("wealId"));
                if (maplist != null) {
                    for (Map<String, Object> groupMap : maplist) {
                        if (groupMap.get("type") != null) {
                            Integer type = (Integer) groupMap.get("type");
                            if (type == 1) {
                                isNew = false;// 表示该用户已经查看
                            } else {
                                isApply = true;// 表示该用户已经报名
                            }
                        }
                    }
                }
                map.put("isApply", isApply);
                map.put("isNew", isNew);
                if(map.get("imgurl") !=null && StringUtils.isNotBlank(map.get("imgurl").toString())){
                    map.put("pics",map.get("imgurl").toString().split(","));
                }else{
                    map.put("pics", null);
                }
            }
        }
        return new QueryResult<Map<String, Object>>(new PageInfo<Map<String, Object>>(list));
    }

    @Override
    public Object queryWeals(WealVO weal) {
        if (weal.getPageNo() != null && weal.getPageSize() != null) {
            PageHelper.startPage(weal.getPageNo(), weal.getPageSize(), "w.ctime desc");
        }
        List<Map<String, Object>> list = wealMapper.queryWeals(weal);// 如果userId不为空，则查询该用户ID报名的福利
        return new QueryResult<Map<String, Object>>(new PageInfo<Map<String, Object>>(list));
    }

    @Override
    public Object delete(WealVO weal) {

        return null;
    }

}
