package com.karakal.controller.material.vo;


import java.util.List;

import com.karakal.entity.Material;

public class MaterialVo extends Material {
    
    private String keyword;//关键字：设备名称，设备类型查找
    
    
    public String getKeyword() {
        return keyword;
    }

    
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }


    private List<Material> materials;
    
    private List<String> materialIds;
    
    
    
    public List<String> getMaterialIds() {
        return materialIds;
    }


    
    public void setMaterialIds(List<String> materialIds) {
        this.materialIds = materialIds;
    }


    public List<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

    private String fromUserId;//发放人
    
    private String toUserId;//接收人
    
    private String remark;//物资单描述
    
    private String picurl;//图片地址
    
    public String getRemark() {
        return remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    public String getPicurl() {
        return picurl;
    }
    
    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getFromUserId() {
        return fromUserId;
    }
    
    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }
    
    public String getToUserId() {
        return toUserId;
    }


    
    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }


    private Integer pageNo = 1;


    public int getIsQueryDetail() {
        return isQueryDetail;
    }

    public void setIsQueryDetail(int isQueryDetail) {
        this.isQueryDetail = isQueryDetail;
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

    private Integer type;//2:发放，1：交接

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
