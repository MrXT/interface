package com.karakal.controller.system;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.karakal.constant.SystemConstant;
import com.karakal.controller.system.vo.UserVo;
import com.karakal.entity.Department;
import com.karakal.exception.BusinessException;
import com.karakal.service.system.DepartmentService;

/**
 * ClassName: DepartmentController <br/>
 * @author xt(di.xiao@karakal.com.cn)
 * @date 2016年11月5日
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/department")
public class DepartmentController {
    @Resource
    private Map<String, String> statusMap;
    @Autowired
    public DepartmentService departmentService;
    
    @RequestMapping("query")
    public Object query(Department department){
        return departmentService.query(department);
    }
    /**
     * 查询某部门下的人员
     * @param department
     * @return
     */
    @RequestMapping("queryUser")
    public Object queryUserByDId(UserVo userVo){
        if( StringUtils.isBlank(userVo.getUserId())){
            throw new BusinessException(statusMap.get(SystemConstant.NULL_MUSTPARAMETER));
        }
        return departmentService.queryUserByDId(userVo);
    }
}
