package com.karakal.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bs_login")
public class Login {
    @Id
    @Column(name = "login_id")
    private Integer loginId;

    @Column(name = "user_id")
    private String userId;

    /**
     * 登录时间
     */
    private Date ctime;

    /**
     * 登录日期
     */
    private Date cdate;

    /**
     * @return login_id
     */
    public Integer getLoginId() {
        return loginId;
    }

    /**
     * @param loginId
     */
    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
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
     * 获取登录时间
     *
     * @return ctime - 登录时间
     */
    public Date getCtime() {
        return ctime;
    }

    /**
     * 设置登录时间
     *
     * @param ctime 登录时间
     */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /**
     * 获取登录日期
     *
     * @return cdate - 登录日期
     */
    public Date getCdate() {
        return cdate;
    }

    /**
     * 设置登录日期
     *
     * @param cdate 登录日期
     */
    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }
}