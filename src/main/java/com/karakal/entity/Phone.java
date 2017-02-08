package com.karakal.entity;

import javax.persistence.*;

@Table(name = "bs_phone")
public class Phone {
    @Id
    @Column(name = "PHONE_ID")
    private String phoneId;

    @Column(name = "CPHONE")
    private String cphone;

    @Column(name = "MAIN_LINE")
    private String mainLine;

    @Column(name = "BACK_LINE")
    private String backLine;

    @Column(name = "USER_ID")
    private String userId;

    /**
     * 0:未分配
1:已分配
     */
    @Column(name = "STATUS")
    private String status;

    /**
     * 备注
     */
    @Column(name = "REMARK")
    private String remark;

    /**
     * @return PHONE_ID
     */
    public String getPhoneId() {
        return phoneId;
    }

    /**
     * @param phoneId
     */
    public void setPhoneId(String phoneId) {
        this.phoneId = phoneId;
    }

    /**
     * @return CPHONE
     */
    public String getCphone() {
        return cphone;
    }

    /**
     * @param cphone
     */
    public void setCphone(String cphone) {
        this.cphone = cphone;
    }

    /**
     * @return MAIN_LINE
     */
    public String getMainLine() {
        return mainLine;
    }

    /**
     * @param mainLine
     */
    public void setMainLine(String mainLine) {
        this.mainLine = mainLine;
    }

    /**
     * @return BACK_LINE
     */
    public String getBackLine() {
        return backLine;
    }

    /**
     * @param backLine
     */
    public void setBackLine(String backLine) {
        this.backLine = backLine;
    }

    /**
     * @return USER_ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取0:未分配
1:已分配
     *
     * @return STATUS - 0:未分配
1:已分配
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置0:未分配
1:已分配
     *
     * @param status 0:未分配
1:已分配
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取备注
     *
     * @return REMARK - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}