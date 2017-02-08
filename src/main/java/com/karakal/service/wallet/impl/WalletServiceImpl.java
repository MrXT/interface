package com.karakal.service.wallet.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.karakal.commons.bean.QueryResult;
import com.karakal.controller.wallet.vo.AccountVo;
import com.karakal.dao.mapper.AccountMapper;
import com.karakal.dao.mapper.ComsumeTimeslotMapper;
import com.karakal.dao.mapper.RecordMapper;
import com.karakal.entity.Account;
import com.karakal.entity.ComsumeTimeslot;
import com.karakal.entity.Record;
import com.karakal.exception.BusinessException;
import com.karakal.service.wallet.WalletService;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private ComsumeTimeslotMapper comsumeTimeslotMapper;

    @Override
    public Object queryAcount(Account account) {
        return accountMapper.select(account);
    }

    @Override
    public Object queryRecord(AccountVo account) {
        if (account.getPageNo() != null && account.getPageSize() != null) {
            PageHelper.startPage(account.getPageNo(), account.getPageSize(), "ctime desc");
        }
        return new QueryResult<Record>(new PageInfo<Record>(recordMapper.queryByCondition(account)));
    }

    @Transactional(rollbackFor = { Exception.class })
    @Override
    public Integer saveRecord(AccountVo account) {
        if (account.getType() == 5) {//用餐消费，查询时间段内的设定的消费金额
             ComsumeTimeslot comsumeTimeslot = new ComsumeTimeslot();
             comsumeTimeslot.setStartDate(new Date());
             comsumeTimeslot = comsumeTimeslotMapper.queryByContidion(comsumeTimeslot);
             if(comsumeTimeslot == null || comsumeTimeslot.getComsumeTimeslotId() == null){
                 throw new BusinessException("消费失败，当前时间不能用餐!");
             }
             Map<String,Object> map = new HashMap<>();
             map.put("startDate", comsumeTimeslot.getStartDate());
             map.put("endDate", comsumeTimeslot.getEndDate());
             map.put("userId", account.getUserId());
             map.put("currendate", new Date());
             List<Record> records = recordMapper.queryByTimeSlot(map);
             if(records.size() == 0){//如果在这段时间内没消费
                 account.setMoney(comsumeTimeslot.getFirstMoney());
             }else if (records.size()>=1){
                 account.setMoney(comsumeTimeslot.getSecondMoney());
             }
        }
        Account userAccount = new Account();
        userAccount.setUserId(account.getUserId());
        List<Account> accounts = accountMapper.select(userAccount);
        if (accounts.size() > 0) {
            userAccount = accounts.get(0);
        } else {
            throw new BusinessException("未找到账户！");
        }
        Record record = new Record();
        record.setMoney(account.getMoney());
        record.setRecordUserId(account.getUserId());
        if (account.getType() == 2) {// 充值
            throw new BusinessException("TYPE参数错误！");
            // userAccount.setRechange(userAccount.getRechange()+account.getMoney());
            // userAccount.setBalance(userAccount.getBalance()+account.getMoney());
            // record.setMessage("成功充值"+account.getMoney()+"元。");
        } else if (account.getType() == 3 || account.getType() == 5 || account.getType() == 4) {
            userAccount.setComsume(userAccount.getComsume() + account.getMoney());
            if (userAccount.getBalance() - account.getMoney() >= 0) {// 如果余额大于消费金额
                userAccount.setBalance(userAccount.getBalance() - account.getMoney());
            } else {
                throw new BusinessException("余额不足，请充值！");
            }
            record.setMessage("成功消费" + account.getMoney() + "元。");
        } else {
            throw new BusinessException("TYPE参数错误！");
        }
        record.setType(Byte.parseByte(account.getType().toString()));
        record.setCtime(new Date());
        accountMapper.updateByPrimaryKeySelective(userAccount);
        recordMapper.insertUseGeneratedKeys(record);
        return record.getId();
    }

    @Override
    public Map<String, Object> queryRecord(Integer recordId) {
        
        return recordMapper.queryRecord(recordId);
    }

}
