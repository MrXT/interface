
package com.karakal.controller.weal;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karakal.constant.SystemConstant;
import com.karakal.controller.weal.vo.WealVO;
import com.karakal.exception.BusinessException;
import com.karakal.service.weal.WealService;


@RequestMapping("/weal")
@RestController
public class WealController {
    @Autowired
    private WealService wealService;
    
    @Resource
    private Map<String, String> statusMap;
    
    @RequestMapping("saveUser")
    public Object saveUser(WealVO weal){
        if(StringUtils.isBlank(weal.getWealId())){
            throw new BusinessException(statusMap.get(SystemConstant.NULL_MUSTPARAMETER));
        }
        return wealService.saveUser(weal);
    }
    @RequestMapping("query")
    public Object query(WealVO weal){
        if(StringUtils.isBlank(weal.getUserId())){
            throw new BusinessException(statusMap.get(SystemConstant.NULL_MUSTPARAMETER));
        }
        return wealService.query(weal);
    }
    @RequestMapping("queryWeals")
    public Object queryRecord(WealVO weal){
        if(StringUtils.isBlank(weal.getWealId())){
            throw new BusinessException(statusMap.get(SystemConstant.NULL_MUSTPARAMETER));
        }
        return wealService.queryWeals(weal);
    }
    @RequestMapping("delete")
    public Object delete(WealVO weal){
        return wealService.delete(weal);
    }
}


