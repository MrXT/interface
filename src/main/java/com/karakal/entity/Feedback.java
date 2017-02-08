package com.karakal.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bs_feedback")
public class Feedback {
    @Id
    @Column(name = "feedback_id")
    private Integer feedbackId;

    /**
     * 图片地址
     */
    private String pic;

    /**
     * 信息
     */
    private String info;

    /**
     * 提交人ID
     */
    @Column(name = "user_id")
    private String userId;

    private Date ctime;

    /**
     * @return feedback_id
     */
    public Integer getFeedbackId() {
        return feedbackId;
    }

    /**
     * @param feedbackId
     */
    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    /**
     * 获取图片地址
     *
     * @return pic - 图片地址
     */
    public String getPic() {
        return pic;
    }

    /**
     * 设置图片地址
     *
     * @param pic 图片地址
     */
    public void setPic(String pic) {
        this.pic = pic;
    }

    /**
     * 获取信息
     *
     * @return info - 信息
     */
    public String getInfo() {
        return info;
    }

    /**
     * 设置信息
     *
     * @param info 信息
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * 获取提交人ID
     *
     * @return user_id - 提交人ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置提交人ID
     *
     * @param userId 提交人ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
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
}