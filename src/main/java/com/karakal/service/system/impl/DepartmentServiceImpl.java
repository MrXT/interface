
package com.karakal.service.system.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karakal.controller.system.vo.UserVo;
import com.karakal.dao.mapper.DepartmentMapper;
import com.karakal.dao.mapper.UserMapper;
import com.karakal.entity.Department;
import com.karakal.service.system.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;
    
    @Autowired
    private UserMapper userMapper;

    @Override
    public Object query(Department department) {
        List<Department> departments = departmentMapper.select(department);
        return departments;
    }

    @Override
    public Object queryUserByDId(UserVo user) {
        UserVo userQuery = new UserVo();
        userQuery.setUserId(user.getUserId());
        UserVo currenUser = userMapper.queryUserByDId(userQuery).get(0);//当前用户
        user.setUserId(null);
        List<UserVo> users = userMapper.queryUserByDId(user);//所有用户
        for (UserVo userVo : users) {
            if(StringUtils.isBlank(currenUser.getPosition()) || "普通员工".equals(currenUser.getPosition())){
                userVo.setPhone(userVo.getIspublish()==0?"******":userVo.getPhone());
            }else if("部门领导".equals(currenUser.getPosition())){
                if(!currenUser.getDepartmentId().equals(userVo.getDepartmentId())){
                    userVo.setPhone(userVo.getIspublish()==0?"******":userVo.getPhone());
                }
            }
        }
        return users;
    }

}

