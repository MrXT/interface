package com.karakal.entity;

import javax.persistence.*;

@Table(name = "bs_remind")
public class Remind {
    @Id
    @Column(name = "REMIND_ID")
    private String remindId;

    /**
     * 10001：注册提醒
10002：工单提醒
10003：会议提醒
     */
    @Column(name = "TYPE")
    private String type;

    /**
     * 用户ID
     */
    @Column(name = "USER_ID")
    private String userId;

    /**
     * @return REMIND_ID
     */
    public String getRemindId() {
        return remindId;
    }

    /**
     * @param remindId
     */
    public void setRemindId(String remindId) {
        this.remindId = remindId;
    }

    /**
     * 获取10001：注册提醒
10002：工单提醒
10003：会议提醒
     *
     * @return TYPE - 10001：注册提醒
10002：工单提醒
10003：会议提醒
     */
    public String getType() {
        return type;
    }

    /**
     * 设置10001：注册提醒
10002：工单提醒
10003：会议提醒
     *
     * @param type 10001：注册提醒
10002：工单提醒
10003：会议提醒
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取用户ID
     *
     * @return USER_ID - 用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
}