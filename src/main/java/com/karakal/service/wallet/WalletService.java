
package com.karakal.service.wallet;

import java.util.Map;

import com.karakal.controller.wallet.vo.AccountVo;
import com.karakal.entity.Account;


public interface WalletService {

    Object queryAcount(Account account);

    Object queryRecord(AccountVo account);

    Integer saveRecord(AccountVo account);

    Map<String, Object> queryRecord(Integer recordId);

}

