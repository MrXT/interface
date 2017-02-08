
package com.karakal.controller.wallet.vo;

import com.karakal.entity.Account;


public class AccountVo extends Account{
    private Integer type;//1：充值记录，2：消费记录
    
    public Integer getType() {
        return type;
    }


    
    public void setType(Integer type) {
        this.type = type;
    }

    private Integer pageNo = 1;
    
    private Integer pageSize;
    
    
    public Integer getPageNo() {
        return pageNo;
    }

    
    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    
    public Integer getPageSize() {
        return pageSize;
    }

    
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    private Double money;
    
    private String code;//付款码
    
    public Double getMoney() {
        return money;
    }
    
    public void setMoney(Double money) {
        this.money = money;
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    
    
    

}

