package com.karakal.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bs_material_record")
public class MaterialRecord {
    @Id
    @Column(name = "record_id")
    private Integer recordId;

    @Column(name = "material_id")
    private String materialId;

    /**
     * 发放人或者接收人
     */
    @Column(name = "from_user_id")
    private String fromUserId;

    /**
     * 接送人
     */
    @Column(name = "to_user_id")
    private String toUserId;

    private Date ctime;

    @Column(name = "from_department_id")
    private String fromDepartmentId;

    @Column(name = "to_department_id")
    private String toDepartmentId;

    /**
     * 1：交接
2：发放
     */
    private Integer type;

    /**
     * @return record_id
     */
    public Integer getRecordId() {
        return recordId;
    }

    /**
     * @param recordId
     */
    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
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
     * 获取发放人或者接收人
     *
     * @return from_user_id - 发放人或者接收人
     */
    public String getFromUserId() {
        return fromUserId;
    }

    /**
     * 设置发放人或者接收人
     *
     * @param fromUserId 发放人或者接收人
     */
    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    /**
     * 获取接送人
     *
     * @return to_user_id - 接送人
     */
    public String getToUserId() {
        return toUserId;
    }

    /**
     * 设置接送人
     *
     * @param toUserId 接送人
     */
    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    /**
     * @return ctime
     */
    public Date getCtime() {
        return ctime;
    }

    /**
     * @param ctime
     */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /**
     * @return from_department_id
     */
    public String getFromDepartmentId() {
        return fromDepartmentId;
    }

    /**
     * @param fromDepartmentId
     */
    public void setFromDepartmentId(String fromDepartmentId) {
        this.fromDepartmentId = fromDepartmentId;
    }

    /**
     * @return to_department_id
     */
    public String getToDepartmentId() {
        return toDepartmentId;
    }

    /**
     * @param toDepartmentId
     */
    public void setToDepartmentId(String toDepartmentId) {
        this.toDepartmentId = toDepartmentId;
    }

    /**
     * 获取1：交接
2：发放
     *
     * @return type - 1：交接
2：发放
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置1：交接
2：发放
     *
     * @param type 1：交接
2：发放
     */
    public void setType(Integer type) {
        this.type = type;
    }
}