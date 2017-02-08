package com.karakal.entity;

import javax.persistence.*;

@Table(name = "bs_distribute_user")
public class DistributeUser {
    @Id
    @Column(name = "distribute_user_id")
    private Integer distributeUserId;

    @Column(name = "distribute_id")
    private String distributeId;

    @Column(name = "user_id")
    private String userId;

    /**
     * @return distribute_user_id
     */
    public Integer getDistributeUserId() {
        return distributeUserId;
    }

    /**
     * @param distributeUserId
     */
    public void setDistributeUserId(Integer distributeUserId) {
        this.distributeUserId = distributeUserId;
    }

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
}