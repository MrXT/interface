
package com.karakal.service.system;

import com.karakal.controller.common.MessageVo;
import com.karakal.controller.system.vo.UserVo;
import com.karakal.entity.Feedback;


public interface UserService {
    /**
     * 
     * @param phoneNum
     * @return
     */
    boolean queryUser(String phoneNum);

    Object login(UserVo user);

    Object save(UserVo user);

    Object saveUser(UserVo user);

    Object getMessages(MessageVo message);

    Object updateMessageStatus(MessageVo message);

    Object updateMyAllMessageStatus(MessageVo message);

    Object queryVersion(Integer versionCode,Integer type);

    Object saveFeedBack(Feedback feedback);
    
}

