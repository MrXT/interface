package com.karakal.entity;

import javax.persistence.*;

@Table(name = "bs_topic_user")
public class TopicUser {
    @Id
    @Column(name = "topic_user_id")
    private Integer topicUserId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "topic_id")
    private String topicId;

    /**
     * 0:点赞
1:查看
     */
    private Integer type;

    /**
     * @return topic_user_id
     */
    public Integer getTopicUserId() {
        return topicUserId;
    }

    /**
     * @param topicUserId
     */
    public void setTopicUserId(Integer topicUserId) {
        this.topicUserId = topicUserId;
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
     * @return topic_id
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
     * 获取0:点赞
1:查看
     *
     * @return type - 0:点赞
1:查看
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置0:点赞
1:查看
     *
     * @param type 0:点赞
1:查看
     */
    public void setType(Integer type) {
        this.type = type;
    }
}