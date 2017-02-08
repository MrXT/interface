
package com.karakal.controller.distribute;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karakal.controller.distribute.vo.DistributeVo;
import com.karakal.exception.BusinessException;
import com.karakal.service.distribute.DistributeService;

@RequestMapping("distribute")
@RestController
public class DistributeController {
    @Autowired
    private DistributeService distributeService;
    @RequestMapping("query")
    public Object query(DistributeVo distribute){
        return distributeService.query(distribute);
    }
    /**
     * 查询用户是否还有未评价的单子
     * @param userId
     * @return
     */
    @RequestMapping("queryNoEvaluate")
    public Object queryNoEvaluate(DistributeVo distribute){
        if(StringUtils.isBlank(distribute.getUserId()) || StringUtils.isBlank(distribute.getType())){
            throw new BusinessException("必要的参数不能为空！");
        }
        return distributeService.queryNoEvaluate(distribute).size()==0?false:true;
    }
}

