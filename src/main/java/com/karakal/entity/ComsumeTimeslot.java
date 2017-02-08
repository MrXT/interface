package com.karakal.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bs_comsume_timeslot")
public class ComsumeTimeslot {
    @Id
    @Column(name = "comsume_timeslot_id")
    private String comsumeTimeslotId;

    /**
     * 消费时间起
     */
    @Column(name = "start_date")
    private Date startDate;

    /**
     * 消费时间止
     */
    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "first_money")
    private Double firstMoney;

    @Column(name = "second_money")
    private Double secondMoney;

    /**
     * @return comsume_timeslot_id
     */
    public String getComsumeTimeslotId() {
        return comsumeTimeslotId;
    }

    /**
     * @param comsumeTimeslotId
     */
    public void setComsumeTimeslotId(String comsumeTimeslotId) {
        this.comsumeTimeslotId = comsumeTimeslotId;
    }

    /**
     * 获取消费时间起
     *
     * @return start_date - 消费时间起
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置消费时间起
     *
     * @param startDate 消费时间起
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 获取消费时间止
     *
     * @return end_date - 消费时间止
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置消费时间止
     *
     * @param endDate 消费时间止
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return first_money
     */
    public Double getFirstMoney() {
        return firstMoney;
    }

    /**
     * @param firstMoney
     */
    public void setFirstMoney(Double firstMoney) {
        this.firstMoney = firstMoney;
    }

    /**
     * @return second_money
     */
    public Double getSecondMoney() {
        return secondMoney;
    }

    /**
     * @param secondMoney
     */
    public void setSecondMoney(Double secondMoney) {
        this.secondMoney = secondMoney;
    }
}