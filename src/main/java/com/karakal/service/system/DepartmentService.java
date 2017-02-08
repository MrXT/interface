
package com.karakal.service.system;

import com.karakal.controller.system.vo.UserVo;
import com.karakal.entity.Department;


public interface DepartmentService {

    Object query(Department department);

    Object queryUserByDId(UserVo user);

}

