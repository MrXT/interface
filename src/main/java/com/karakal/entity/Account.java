package com.karakal.entity;

import javax.persistence.*;

@Table(name = "bs_account")
public class Account {
    @Id
    @Column(name = "account_id")
    private String accountId;

    @Column(name = "user_id")
    private String userId;

    /**
     * 余额
     */
    private Double balance;

    /**
     * 总共充值
     */
    private Double rechange;

    /**
     * 总共消费
     */
    private Double comsume;

    /**
     * @return account_id
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * @param accountId
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
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
     * 获取余额
     *
     * @return balance - 余额
     */
    public Double getBalance() {
        return balance;
    }

    /**
     * 设置余额
     *
     * @param balance 余额
     */
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    /**
     * 获取总共充值
     *
     * @return rechange - 总共充值
     */
    public Double getRechange() {
        return rechange;
    }

    /**
     * 设置总共充值
     *
     * @param rechange 总共充值
     */
    public void setRechange(Double rechange) {
        this.rechange = rechange;
    }

    /**
     * 获取总共消费
     *
     * @return comsume - 总共消费
     */
    public Double getComsume() {
        return comsume;
    }

    /**
     * 设置总共消费
     *
     * @param comsume 总共消费
     */
    public void setComsume(Double comsume) {
        this.comsume = comsume;
    }
}