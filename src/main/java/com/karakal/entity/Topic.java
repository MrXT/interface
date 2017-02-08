package com.karakal.entity;

import javax.persistence.*;

@Table(name = "bs_topic")
public class Topic {
    @Id
    @Column(name = "TOPIC_ID")
    private String topicId;

    /**
     * 帖子标题
     */
    @Column(name = "TITLE")
    private String title;

    /**
     * 帖子内容
     */
    @Column(name = "CONTENT")
    private String content;

    /**
     * 发帖人
     */
    @Column(name = "USER_ID")
    private String userId;

    /**
     * 发帖时间
     */
    @Column(name = "CTIME")
    private String ctime;

    /**
     * 点赞数
     */
    @Column(name = "PRAISE_COUNT")
    private Integer praiseCount;

    /**
     * 查看数
     */
    @Column(name = "SCAN_COUNT")
    private Integer scanCount;

    /**
     * @return TOPIC_ID
     */
    public String getTopicId() {
        return topicId;
    }

    /**
     * @param topicId
     */
    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    /**
     * 获取帖子标题
     *
     * @return TITLE - 帖子标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置帖子标题
     *
     * @param title 帖子标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取帖子内容
     *
     * @return CONTENT - 帖子内容
     */
    public String getContent() {
        return content;
    }
    @Column(name = "pics")
    private String pics;
    
    

    
    public String getPics() {
        return pics;
    }

    
    public void setPics(String pics) {
        this.pics = pics;
    }

    /**
     * 设置帖子内容
     *
     * @param content 帖子内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取发帖人
     *
     * @return USER_ID - 发帖人
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置发帖人
     *
     * @param userId 发帖人
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取发帖时间
     *
     * @return CTIME - 发帖时间
     */
    public String getCtime() {
        return ctime;
    }

    /**
     * 设置发帖时间
     *
     * @param ctime 发帖时间
     */
    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    /**
     * 获取点赞数
     *
     * @return PRAISE_COUNT - 点赞数
     */
    public Integer getPraiseCount() {
        return praiseCount;
    }

    /**
     * 设置点赞数
     *
     * @param praiseCount 点赞数
     */
    public void setPraiseCount(Integer praiseCount) {
        this.praiseCount = praiseCount;
    }

    /**
     * 获取查看数
     *
     * @return SCAN_COUNT - 查看数
     */
    public Integer getScanCount() {
        return scanCount;
    }

    /**
     * 设置查看数
     *
     * @param scanCount 查看数
     */
    public void setScanCount(Integer scanCount) {
        this.scanCount = scanCount;
    }
}