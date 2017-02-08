package com.karakal.controller.wallet;

import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import nl.justobjects.pushlet.core.Dispatcher;
import nl.justobjects.pushlet.core.Event;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import redis.clients.jedis.JedisCommands;

import com.alibaba.fastjson.JSON;
import com.karakal.controller.system.vo.UserVo;
import com.karakal.controller.wallet.vo.AccountVo;
import com.karakal.entity.Account;
import com.karakal.exception.BusinessException;
import com.karakal.service.wallet.WalletService;
import com.karakal.util.LogUtil;
import com.karakal.util.UuidUtil;

@RequestMapping("/wallet")
@RestController
public class WalletController {

    @Autowired
    private WalletService walletService;

    @Resource(name = "jedisCluster")
    private JedisCommands jedis;

    /**
     * 查询账户余额
     * @param account
     * @return
     */
    @RequestMapping("/query")
    public Object query(Account account) {
        if (StringUtils.isBlank(account.getUserId())) {
            throw new BusinessException("必要的参数不能为空!");
        }
        return walletService.queryAcount(account);
    }

    /**
     * 查询账户记录
     * @param account
     * @return
     */
    @RequestMapping("/queryRecord")
    public Object queryRecord(AccountVo account) {
        if (StringUtils.isBlank(account.getUserId()) || account.getType() == null) {
            throw new BusinessException("必要的参数不能为空!");
        }
        return walletService.queryRecord(account);
    }

    @RequestMapping("/saveRecord")
    public Object saveRecord(AccountVo account, HttpSession session) {
        if (StringUtils.isBlank(account.getUserId()) || account.getType() == null) {
            throw new BusinessException("必要的参数不能为空!");
        }
        if (account.getType() != 5 && (account.getMoney()==null || account.getMoney() <= 0)) {// 用餐消费不需要money
            throw new BusinessException("MONEY必须大于0！");
        }
        Object object = session.getAttribute("user");
        String userId = null;
        if (object != null) {
            userId = ((UserVo) object).getUserId();
            if (!userId.equals(account.getUserId()) && account.getType() != 4) {
                throw new BusinessException("登录用户ID与参数USERID不同!");
            }
        } else {
            throw new BusinessException("10","获取用户信息失败!");
        }
        if (account.getType() == 4) {
            if (StringUtils.isBlank(account.getCode())) {
                throw new BusinessException("必要的参数不能为空！");
            }
            String code = jedis.get(account.getUserId());
            if (code != null) {
                if (code.equals(account.getCode())) {
                    jedis.del(account.getUserId());
                } else {
                    throw new BusinessException("付款码二维码错误！");
                }
            } else {
                throw new BusinessException("付款二维码失效！");
            }
        }
        Integer recordId = walletService.saveRecord(account);
        // 页面推送
        if (account.getType() == 5) {// 用餐消费
            try {
                Map<String, Object> map = walletService.queryRecord(recordId);
                map.put("ctime", com.karakal.util.DateUtil.date2String((Date) map.get("ctime"), "HH:mm:ss"));
                Event event = Event.createDataEvent("fee");
                event.setField("data", URLEncoder.encode(JSON.toJSONString(map),"UTF-8"));
                Dispatcher.getInstance().unicast(event, "fee");
            } catch (Exception e) {
                LogUtil.LOGEXCEPTION(e);
            }
        }
        return 1;
    }

    /**
     * 生成付款验证码
     * @param account
     * @return
     */
    @RequestMapping("/getQd")
    public Object getQd(AccountVo account, HttpSession session) {
        Object object = session.getAttribute("user");
        String userId = null;
        if (object != null) {
            userId = ((UserVo) object).getUserId();
        } else {
            throw new BusinessException("10","未登录APP，获取付款码失败！");
        }
        String code = UuidUtil.get32UUID();
        jedis.set(userId, code);
        jedis.expire(userId, 5 * 2);// 在5分钟后失效
        return code;
    }
}
