
package com.karakal.controller.system.vo;

import com.karakal.entity.User;


public class UserVo extends User{
    private String departmentId;
    private String departmentName;
    private String roleName;//角色
    private String stationName;//岗位（职位）
    
    private String keyword;
    
    public String getKeyword() {
        return keyword;
    }


    
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }


    public String getStationName() {
        return stationName;
    }

    
    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    private String code;
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    public String getDepartmentId() {
        return departmentId;
    }
    
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
    
    public String getDepartmentName() {
        return departmentName;
    }
    
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    
    public String getRoleName() {
        return roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
}

