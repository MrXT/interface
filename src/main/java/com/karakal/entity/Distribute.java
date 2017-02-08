package com.karakal.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bs_distribute")
public class Distribute {
    @Id
    @Column(name = "distribute_id")
    private String distributeId;

    /**
     * 工单或者会议ID
     */
    private String rid;

    /**
     * 派单责任人ID
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 0：新增
1：已派单
2：已确认
3：已完成
4：已评价
     */
    private Integer status;

    /**
     * 评价内容
     */
    private String content;

    /**
     * 评价星级
     */
    private Integer star;

    /**
     * 紧急情况
     */
    @Column(name = "IMPORTANT")
    private String important;

    @Column(name = "servicetype_id")
    private String servicetypeId;

    /**
     * 派单备注
     */
    private String remark;

    /**
     * 会议时间
     */
    private Date date;

    /**
     * @return distribute_id
     */
    public String getDistributeId() {
        return distributeId;
    }

    /**
     * @param distributeId
     */
    public void setDistributeId(String distributeId) {
        this.distributeId = distributeId;
    }

    /**
     * 获取工单或者会议ID
     *
     * @return rid - 工单或者会议ID
     */
    public String getRid() {
        return rid;
    }

    /**
     * 设置工单或者会议ID
     *
     * @param rid 工单或者会议ID
     */
    public void setRid(String rid) {
        this.rid = rid;
    }

    /**
     * 获取派单责任人ID
     *
     * @return user_id - 派单责任人ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置派单责任人ID
     *
     * @param userId 派单责任人ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取0：新增
1：已派单
2：已确认
3：已完成
4：已评价
     *
     * @return status - 0：新增
1：已派单
2：已确认
3：已完成
4：已评价
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0：新增
1：已派单
2：已确认
3：已完成
4：已评价
     *
     * @param status 0：新增
1：已派单
2：已确认
3：已完成
4：已评价
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取评价内容
     *
     * @return content - 评价内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置评价内容
     *
     * @param content 评价内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取评价星级
     *
     * @return star - 评价星级
     */
    public Integer getStar() {
        return star;
    }

    /**
     * 设置评价星级
     *
     * @param star 评价星级
     */
    public void setStar(Integer star) {
        this.star = star;
    }

    /**
     * 获取紧急情况
     *
     * @return IMPORTANT - 紧急情况
     */
    public String getImportant() {
        return important;
    }

    /**
     * 设置紧急情况
     *
     * @param important 紧急情况
     */
    public void setImportant(String important) {
        this.important = important;
    }

    /**
     * @return servicetype_id
     */
    public String getServicetypeId() {
        return servicetypeId;
    }

    /**
     * @param servicetypeId
     */
    public void setServicetypeId(String servicetypeId) {
        this.servicetypeId = servicetypeId;
    }

    /**
     * 获取派单备注
     *
     * @return remark - 派单备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置派单备注
     *
     * @param remark 派单备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取会议时间
     *
     * @return date - 会议时间
     */
    public Date getDate() {
        return date;
    }

    /**
     * 设置会议时间
     *
     * @param date 会议时间
     */
    public void setDate(Date date) {
        this.date = date;
    }
}