package com.karakal.entity;

import javax.persistence.*;

@Table(name = "bs_reply")
public class Reply {
    @Id
    @Column(name = "REPLY_ID")
    private String replyId;

    /**
     * 回复内容
     */
    @Column(name = "CONTENT")
    private String content;

    /**
     * 回复时间
     */
    @Column(name = "CTIME")
    private String ctime;

    /**
     * 帖子标题
     */
    @Column(name = "TOPIC_ID")
    private String topicId;

    @Column(name = "USER_ID")
    private String userId;

    /**
     * @return REPLY_ID
     */
    public String getReplyId() {
        return replyId;
    }

    /**
     * @param replyId
     */
    public void setReplyId(String replyId) {
        this.replyId = replyId;
    }

    /**
     * 获取回复内容
     *
     * @return CONTENT - 回复内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置回复内容
     *
     * @param content 回复内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取回复时间
     *
     * @return CTIME - 回复时间
     */
    public String getCtime() {
        return ctime;
    }

    /**
     * 设置回复时间
     *
     * @param ctime 回复时间
     */
    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    /**
     * 获取帖子标题
     *
     * @return TOPIC_ID - 帖子标题
     */
    public String getTopicId() {
        return topicId;
    }

    /**
     * 设置帖子标题
     *
     * @param topicId 帖子标题
     */
    public void setTopicId(String topicId) {
        this.topicId = topicId;
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
}