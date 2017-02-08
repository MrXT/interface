package com.karakal.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bs_weal_user")
public class WealUser {
    @Id
    @Column(name = "weal_user_id")
    private Integer wealUserId;

    @Column(name = "weal_id")
    private String wealId;

    @Column(name = "user_id")
    private String userId;

    private Date ctime;

    /**
     * 1:已查看
2:报名
     */
    private Integer type;

    /**
     * @return weal_user_id
     */
    public Integer getWealUserId() {
        return wealUserId;
    }

    /**
     * @param wealUserId
     */
    public void setWealUserId(Integer wealUserId) {
        this.wealUserId = wealUserId;
    }

    /**
     * @return weal_id
     */
    public String getWealId() {
        return wealId;
    }

    /**
     * @param wealId
     */
    public void setWealId(String wealId) {
        this.wealId = wealId;
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
     * 获取1:已查看
2:报名
     *
     * @return type - 1:已查看
2:报名
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置1:已查看
2:报名
     *
     * @param type 1:已查看
2:报名
     */
    public void setType(Integer type) {
        this.type = type;
    }
}