package com.karakal.entity;

import javax.persistence.*;

@Table(name = "bs_meeting_user")
public class MeetingUser {
    @Id
    @Column(name = "meeting_user_id")
    private Integer meetingUserId;

    @Column(name = "meeting_id")
    private String meetingId;

    @Column(name = "user_id")
    private String userId;

    /**
     * 0:通知
1:报名
2:签到
     */
    private String type;

    /**
     * @return meeting_user_id
     */
    public Integer getMeetingUserId() {
        return meetingUserId;
    }

    /**
     * @param meetingUserId
     */
    public void setMeetingUserId(Integer meetingUserId) {
        this.meetingUserId = meetingUserId;
    }

    /**
     * @return meeting_id
     */
    public String getMeetingId() {
        return meetingId;
    }

    /**
     * @param meetingId
     */
    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
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
     * 获取0:通知
1:报名
2:签到
     *
     * @return type - 0:通知
1:报名
2:签到
     */
    public String getType() {
        return type;
    }

    /**
     * 设置0:通知
1:报名
2:签到
     *
     * @param type 0:通知
1:报名
2:签到
     */
    public void setType(String type) {
        this.type = type;
    }
}