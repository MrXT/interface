package com.karakal.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bs_record")
public class Record {
    @Transient
    private Integer id;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @Column(name = "record_id")
    private Integer recordId;

    /**
     * 本次记录涉及到的金额
     */
    private Double money;

    /**
     * 这条记录影响人ID
     */
    @Column(name = "record_user_id")
    private String recordUserId;

    @Column(name = "handle_user_id")
    private String handleUserId;

    private Date ctime;

    /**
     * 1:退费；2:充值；3:自定义消费4：商家二维码消费
     */
    private Byte type;

    /**
     * 该记录文字信息
     */
    private String message;

    /**
     * @return record_id
     */
    public Integer getRecordId() {
        return recordId;
    }

    /**
     * @param recordId
     */
    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    /**
     * 获取本次记录涉及到的金额
     *
     * @return money - 本次记录涉及到的金额
     */
    public Double getMoney() {
        return money;
    }

    /**
     * 设置本次记录涉及到的金额
     *
     * @param money 本次记录涉及到的金额
     */
    public void setMoney(Double money) {
        this.money = money;
    }

    /**
     * 获取这条记录影响人ID
     *
     * @return record_user_id - 这条记录影响人ID
     */
    public String getRecordUserId() {
        return recordUserId;
    }

    /**
     * 设置这条记录影响人ID
     *
     * @param recordUserId 这条记录影响人ID
     */
    public void setRecordUserId(String recordUserId) {
        this.recordUserId = recordUserId;
    }

    /**
     * @return handle_user_id
     */
    public String getHandleUserId() {
        return handleUserId;
    }

    /**
     * @param handleUserId
     */
    public void setHandleUserId(String handleUserId) {
        this.handleUserId = handleUserId;
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
     * 获取1:退费；2:充值；3:自定义消费4：商家二维码消费
     *
     * @return type - 1:退费；2:充值；3:自定义消费4：商家二维码消费
     */
    public Byte getType() {
        return type;
    }

    /**
     * 设置1:退费；2:充值；3:自定义消费4：商家二维码消费
     *
     * @param type 1:退费；2:充值；3:自定义消费4：商家二维码消费
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * 获取该记录文字信息
     *
     * @return message - 该记录文字信息
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置该记录文字信息
     *
     * @param message 该记录文字信息
     */
    public void setMessage(String message) {
        this.message = message;
    }
}