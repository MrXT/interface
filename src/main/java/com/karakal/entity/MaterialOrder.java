package com.karakal.entity;

import java.util.Date;

import javax.persistence.*;

@Table(name = "bs_material_order")
public class MaterialOrder {
    @Id
    @Column(name = "material_order_id")
    private Integer materialOrderId;

    @Column(name = "material_id")
    private String materialId;
    
    private String picurl;
    private Date ctime;
    
    private Date utime;
    
    
    
    public Date getUtime() {
        return utime;
    }

    
    public void setUtime(Date utime) {
        this.utime = utime;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
    public String getPicurl() {
        return picurl;
    }

    
    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    /**
     * 1:未审批
2:退回
3:已审批
     */
    private Integer status;

    /**
     * 物资类型ID
     */
    @Column(name = "materialtype_id")
    private String materialtypeId;

    @Column(name = "apply_user_id")
    private String applyUserId;

    @Column(name = "handel_user_id")
    private String handelUserId;

    /**
     * 1:申请单
2：挂失单
     */
    private Integer type;

    /**
     * 物资名称ID
     */
    @Column(name = "materialname_id")
    private String materialnameId;

    /**
     * 单子描述
     */
    private String remark;

    /**
     * @return material_order_id
     */
    public Integer getMaterialOrderId() {
        return materialOrderId;
    }

    /**
     * @param materialOrderId
     */
    public void setMaterialOrderId(Integer materialOrderId) {
        this.materialOrderId = materialOrderId;
    }

    /**
     * @return material_id
     */
    public String getMaterialId() {
        return materialId;
    }

    /**
     * @param materialId
     */
    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    /**
     * 获取1:未审批
2:退回
3:已审批
     *
     * @return status - 1:未审批
2:退回
3:已审批
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置1:未审批
2:退回
3:已审批
     *
     * @param status 1:未审批
2:退回
3:已审批
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取物资类型ID
     *
     * @return materialtype_id - 物资类型ID
     */
    public String getMaterialtypeId() {
        return materialtypeId;
    }

    /**
     * 设置物资类型ID
     *
     * @param materialtypeId 物资类型ID
     */
    public void setMaterialtypeId(String materialtypeId) {
        this.materialtypeId = materialtypeId;
    }

    /**
     * @return apply_user_id
     */
    public String getApplyUserId() {
        return applyUserId;
    }

    /**
     * @param applyUserId
     */
    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId;
    }

    /**
     * @return handel_user_id
     */
    public String getHandelUserId() {
        return handelUserId;
    }

    /**
     * @param handelUserId
     */
    public void setHandelUserId(String handelUserId) {
        this.handelUserId = handelUserId;
    }

    /**
     * 获取1:申请单
2：挂失单
     *
     * @return type - 1:申请单
2：挂失单
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置1:申请单
2：挂失单
     *
     * @param type 1:申请单
2：挂失单
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取物资名称ID
     *
     * @return materialname_id - 物资名称ID
     */
    public String getMaterialnameId() {
        return materialnameId;
    }

    /**
     * 设置物资名称ID
     *
     * @param materialnameId 物资名称ID
     */
    public void setMaterialnameId(String materialnameId) {
        this.materialnameId = materialnameId;
    }

    /**
     * 获取单子描述
     *
     * @return remark - 单子描述
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置单子描述
     *
     * @param remark 单子描述
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}