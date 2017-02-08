package com.karakal.dao.mapper;

import org.apache.ibatis.annotations.Param;

import com.karakal.entity.Department;
import com.karakal.util.MyMapper;

public interface DepartmentMapper extends MyMapper<Department> {

    Department selectDepartmentByuserId(@Param("userId") String userId);
}