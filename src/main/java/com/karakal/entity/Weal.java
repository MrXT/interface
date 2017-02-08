package com.karakal.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bs_weal")
public class Weal {
    @Id
    @Column(name = "weal_id")
    private String wealId;

    /**
     * 福利介绍
     */
    private String infor;

    /**
     * 创建时间
     */
    private Date ctime;

    /**
     * 福利标题
     */
    private String title;

    /**
     * 1:上线
0:下架
     */
    private Integer status;

    /**
     * 发布人ID
     */
    @Column(name = "user_id")
    private String userId;

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
     * 获取福利介绍
     *
     * @return infor - 福利介绍
     */
    public String getInfor() {
        return infor;
    }

    /**
     * 设置福利介绍
     *
     * @param infor 福利介绍
     */
    public void setInfor(String infor) {
        this.infor = infor;
    }

    /**
     * 获取创建时间
     *
     * @return ctime - 创建时间
     */
    public Date getCtime() {
        return ctime;
    }

    /**
     * 设置创建时间
     *
     * @param ctime 创建时间
     */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /**
     * 获取福利标题
     *
     * @return title - 福利标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置福利标题
     *
     * @param title 福利标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取1:上线
0:下架
     *
     * @return status - 1:上线
0:下架
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置1:上线
0:下架
     *
     * @param status 1:上线
0:下架
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取发布人ID
     *
     * @return user_id - 发布人ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置发布人ID
     *
     * @param userId 发布人ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
}