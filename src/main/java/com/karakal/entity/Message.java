package com.karakal.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bs_message")
public class Message {
    @Id
    @Column(name = "message_id")
    private Integer messageId;

    private String content;

    /**
     * 10001：注册提醒
10002：工单提醒
10003：会议提醒
10004：会议通知
10005：派单通知
     */
    private String type;

    @Column(name = "user_id")
    private String userId;

    private String title;

    private String rid;

    /**
     * 0：未查看，1：已查看
     */
    private Integer status = 0;

    private Date ctime = new Date();

    /**
     * @return message_id
     */
    public Integer getMessageId() {
        return messageId;
    }

    /**
     * @param messageId
     */
    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取10001：注册提醒
10002：工单提醒
10003：会议提醒
10004：会议通知
10005：派单通知
     *
     * @return type - 10001：注册提醒
10002：工单提醒
10003：会议提醒
10004：会议通知
10005：派单通知
     */
    public String getType() {
        return type;
    }

    /**
     * 设置10001：注册提醒
10002：工单提醒
10003：会议提醒
10004：会议通知
10005：派单通知
     *
     * @param type 10001：注册提醒
10002：工单提醒
10003：会议提醒
10004：会议通知
10005：派单通知
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return user_id
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
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return rid
     */
    public String getRid() {
        return rid;
    }

    /**
     * @param rid
     */
    public void setRid(String rid) {
        this.rid = rid;
    }

    /**
     * 获取0：未查看，1：已查看
     *
     * @return status - 0：未查看，1：已查看
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0：未查看，1：已查看
     *
     * @param status 0：未查看，1：已查看
     */
    public void setStatus(Integer status) {
        this.status = status;
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