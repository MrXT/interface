
package com.karakal.controller.bbs.vo;

import java.util.List;

import com.karakal.entity.Topic;


public class TopicVo extends Topic {
private Integer pageNo = 1;
    
    private List<String> userIds;
    
    private String name;
    
    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }


    private int isAll = 0;
    
    private String date;
    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIsAll() {
        return isAll;
    }


    
    public void setIsAll(int isAll) {
        this.isAll = isAll;
    }


    
    public int getIsQueryDetail() {
        return isQueryDetail;
    }


    
    public void setIsQueryDetail(int isQueryDetail) {
        this.isQueryDetail = isQueryDetail;
    }


    public List<String> getUserIds() {
        return userIds;
    }

    
    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }


    private int isQueryDetail = 0;

    

    private Integer pageSize;

    
    public Integer getPageNo() {
        return pageNo;
    }

    
    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    
    public Integer getPageSize() {
        return pageSize;
    }

    
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    
    private String type;


    
    public String getType() {
        return type;
    }



    
    public void setType(String type) {
        this.type = type;
    }
}

