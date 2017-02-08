package com.karakal.entity;

import java.util.Date;

import javax.persistence.*;



@Table(name = "bs_meeting")
public class Meeting{
    @Id
    @Column(name = "meeting_id")
    private String meetingId;
    @Column(name = "meeting_name")
    private String meetingName;
    
    

    
    public String getMeetingName() {
        return meetingName;
    }

    
    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    @Column(name = "servicetype_id")
    private String servicetypeId;

    @Column(name = "meetingroom_id")
    private String meetingroomId;

    @Column(name = "user_id")
    private String userId;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;

    private Date ctime;

    private String infor;

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
     * @return servicetype_id
     */
    public String getServicetypeId() {
        return servicetypeId;
    }

    /**
     * @param servicetypeId
     */
    public void setServicetypeId(String servicetypeId) {
        this.servicetypeId = servicetypeId;
    }

    /**
     * @return meetingroom_id
     */
    public String getMeetingroomId() {
        return meetingroomId;
    }

    /**
     * @param meetingroomId
     */
    public void setMeetingroomId(String meetingroomId) {
        this.meetingroomId = meetingroomId;
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
     * @return start_date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return end_date
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
     * @return infor
     */
    public String getInfor() {
        return infor;
    }

    /**
     * @param infor
     */
    public void setInfor(String infor) {
        this.infor = infor;
    }
}