package com.karakal.entity;

import javax.persistence.*;

@Table(name = "bs_meetingroom")
public class Meetingroom {
    /**
     * 会议室ID
     */
    @Id
    @Column(name = "meetingroom_id")
    private String meetingroomId;

    /**
     * 会议室名称
     */
    @Column(name = "meetingroom_name")
    private String meetingroomName;

    /**
     * 所在区域
     */
    private String area;

    /**
     * 获取会议室ID
     *
     * @return meetingroom_id - 会议室ID
     */
    public String getMeetingroomId() {
        return meetingroomId;
    }

    /**
     * 设置会议室ID
     *
     * @param meetingroomId 会议室ID
     */
    public void setMeetingroomId(String meetingroomId) {
        this.meetingroomId = meetingroomId;
    }

    /**
     * 获取会议室名称
     *
     * @return meetingroom_name - 会议室名称
     */
    public String getMeetingroomName() {
        return meetingroomName;
    }

    /**
     * 设置会议室名称
     *
     * @param meetingroomName 会议室名称
     */
    public void setMeetingroomName(String meetingroomName) {
        this.meetingroomName = meetingroomName;
    }

    /**
     * 获取所在区域
     *
     * @return area - 所在区域
     */
    public String getArea() {
        return area;
    }

    /**
     * 设置所在区域
     *
     * @param area 所在区域
     */
    public void setArea(String area) {
        this.area = area;
    }
}