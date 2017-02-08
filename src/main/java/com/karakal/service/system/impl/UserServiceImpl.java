package com.karakal.service.system.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.karakal.bean.JPush;
import com.karakal.commons.bean.QueryResult;
import com.karakal.controller.common.MessageVo;
import com.karakal.controller.system.vo.UserVo;
import com.karakal.dao.mapper.AccountMapper;
import com.karakal.dao.mapper.DepartmentStationUserMapper;
import com.karakal.dao.mapper.FeedbackMapper;
import com.karakal.dao.mapper.LoginMapper;
import com.karakal.dao.mapper.MessageMapper;
import com.karakal.dao.mapper.PhoneMapper;
import com.karakal.dao.mapper.RemindMapper;
import com.karakal.dao.mapper.UserMapper;
import com.karakal.dao.mapper.VersionMapper;
import com.karakal.entity.Account;
import com.karakal.entity.DepartmentStationUser;
import com.karakal.entity.Feedback;
import com.karakal.entity.Login;
import com.karakal.entity.Message;
import com.karakal.entity.Phone;
import com.karakal.entity.Remind;
import com.karakal.entity.User;
import com.karakal.entity.Version;
import com.karakal.exception.BusinessException;
import com.karakal.service.system.UserService;
import com.karakal.util.MD5;
import com.karakal.util.UuidUtil;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private JPush jpush;
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private RemindMapper remindMapper;
    
    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private PhoneMapper phoneMapper;
    
    @Autowired
    private AccountMapper accountMapper;
    
    @Autowired
    private FeedbackMapper feedbackMapper;

    @Autowired
    private DepartmentStationUserMapper departmentStationUserMapper;
    
    @Autowired
    private VersionMapper versionMapper;
    
    @Value("${downloadurl}")
    private String downloadurl;

    @Override
    public boolean queryUser(String phoneNum) {
        User user = new User();
        user.setPhone(phoneNum);
        return userMapper.select(user).size() == 0 ? false : true;
    }

    @Override
    public Object login(UserVo user) {
        User userVO = new User();
        String password = user.getPassword().toUpperCase();
        userVO.setPhone(user.getPhone());
        List<UserVo> users = userMapper.selectDetail(userVO);
        if (users.size() != 0) {
            if (password.equals(users.get(0).getPassword())) {
                if (users.get(0).getStatus().equals("1")) {
                    users.get(0).setPassword(null);
                    if(users.get(0).getRoleName().contains("物资管理员")){
                        users.get(0).setRoleName("物资管理员");
                    }
                    //产生记录记录
                    Login login = new Login();
                    login.setUserId(users.get(0).getUserId());
                    login.setCdate(new Date());
                    loginMapper.insertSelective(login);
                    return users.get(0);
                } else if (users.get(0).getStatus().equals("0")) {
                    users.get(0).setPassword(null);
                    //产生记录记录
                    Login login = new Login();
                    login.setUserId(users.get(0).getUserId());
                    login.setCdate(new Date());
                    loginMapper.insertSelective(login);
                    return users.get(0);
//                    throw new BusinessException("管理员还未激活该帐号！");
                } else {
                    throw new BusinessException("该帐号已被禁用！");
                }
            } else {
                throw new BusinessException("密码错误！");
            }
        } else {
            throw new BusinessException("该用户不存在！");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Object save(UserVo user) {
        if (user.getUserId() == null) {
            if (queryUser(user.getPhone())) {
                throw new BusinessException("该号码已被注册！");
            }
            String userId = UuidUtil.get32UUID();
            user.setUserId(userId);
            user.setUsername(user.getPhone());
            user.setPassword(MD5.md5(user.getPassword()).toUpperCase());
            if (StringUtils.isNotBlank(user.getCphone())) {// 添加座机
                Phone phone = new Phone();
                phone.setCphone(user.getCphone());
                List<Phone> phones = phoneMapper.select(phone);
                phone.setStatus("1");
                phone.setUserId(userId);
                if (phones.size() == 1 && phones.get(0).getStatus().equals(0)) {
                    phoneMapper.updateByPrimaryKeySelective(phone);
                } else {
                    phone.setPhoneId(UuidUtil.get32UUID());
                    phoneMapper.insertSelective(phone);
                }
            }
            user.setStatus("0");// 未激活状态
            userMapper.insertSelective(user);
            DepartmentStationUser departmentStationUser = new DepartmentStationUser();
            departmentStationUser.setUserId(user.getUserId());
            departmentStationUser.setDepartmentId(user.getDepartmentId());
            departmentStationUserMapper.insertSelective(departmentStationUser);
            Remind remind = new Remind();
            remind.setType("10001");//注册提醒
            List<Remind> reminds = remindMapper.select(remind);
            String userIds [] = new String[reminds.size()];
            int i = 0;
            for (Remind remindvo : reminds) {
                userIds[i++] = remindvo.getUserId();
            }
            Map<String,Object> map = new HashMap<>();
            map.put("rid", userId);
            map.put("type","10001" );
            map.put("content", "新用户"+user.getName()+"已经注册，请及时激活。");
            List<Message> messages = new ArrayList<Message>();
            if(userIds.length != 0 && jpush.sendMsg(userIds,JSON.toJSONString(map))){
                for (Remind remindvo : reminds) {
                    Message message = new Message();
                    message.setContent("新用户"+user.getName()+"已经注册，请及时激活。");
                    message.setType(remindvo.getType());
                    message.setRid(userId);
                    message.setUserId(remindvo.getUserId());
                    messages.add(message);
                }
                if(messages.size() != 0){
                    messageMapper.insertList(messages);
                }
            }
            //添加账户信息
            Account account = new Account();
            account.setAccountId(UuidUtil.get32UUID());
            account.setUserId(userId);
            accountMapper.insertSelective(account);
        } else {
            User userVo = userMapper.selectByPrimaryKey(user.getUserId());
            if (StringUtils.isNotBlank(user.getCphone()) && !user.getCphone().equals(userVo.getCphone())) {// 添加座机
                Phone phone = new Phone();
                phone.setCphone(userVo.getCphone());
                List<Phone> phoneVOs = phoneMapper.select(phone);
                if (phoneVOs.size() == 1) {
                    phone = phoneVOs.get(0);
                    phone.setStatus("0");
                    phone.setUserId(null);
                    phoneMapper.updateByPrimaryKey(phone);
                } else {
                    phone = new Phone();
                    phone.setCphone(userVo.getCphone());
                    phone.setStatus("1");
                    phone.setUserId(user.getUserId());
                    phoneMapper.delete(phone);// 先删除之前的
                }
                phone = new Phone();
                phone.setCphone(user.getCphone());
                phone.setStatus(null);
                phone.setUserId(null);
                List<Phone> phones = phoneMapper.select(phone);
                phone.setStatus("1");
                phone.setUserId(user.getUserId());
                if (phones.size() == 1 && phones.get(0).getStatus().equals("0")) {
                    phone.setPhoneId(phones.get(0).getPhoneId());
                    phoneMapper.updateByPrimaryKeySelective(phone);
                } else {
                    phone.setPhoneId(UuidUtil.get32UUID());
                    phoneMapper.insertSelective(phone);
                }
            }
            if(StringUtils.isNotBlank(user.getPhone())){
                user.setUsername(user.getPhone());
            }
            userMapper.updateByPrimaryKeySelective(user);
            if (StringUtils.isNotBlank(user.getDepartmentId())) {
                DepartmentStationUser departmentStationUser = new DepartmentStationUser();
                departmentStationUser.setUserId(user.getUserId());
                departmentStationUser = departmentStationUserMapper.selectOne(departmentStationUser);
                departmentStationUser.setDepartmentId(user.getDepartmentId());
                departmentStationUserMapper.updateByPrimaryKeySelective(departmentStationUser);
            }
        }
        UserVo userVo = new UserVo();
        userVo.setUserId(user.getUserId());
        List<UserVo> users = userMapper.selectDetail(userVo);
        users.get(0).setPassword(null);
        return users.get(0);
    }

    @Override
    public Object saveUser(UserVo user) {
        User userinfo = new User();
        userinfo.setPhone(user.getPhone());
        List<User> userinfos = userMapper.select(userinfo);
        if(userinfos.size()==0){
            throw new BusinessException("系统没有找到该手机号！");
        }
        userinfo = userinfos.get(0);
        userinfo.setPassword(MD5.md5(user.getPassword()).toUpperCase());
        return userMapper.updateByPrimaryKeySelective(userinfo);
    }

    @Override
    public Object getMessages(MessageVo message) {
        if (message.getPageNo() != null && message.getPageSize() != null) {
            PageHelper.startPage(message.getPageNo(), message.getPageSize(), "ctime desc");
        }
        message.setCtime(null);
        return new QueryResult<Message>(new PageInfo<Message>(messageMapper.select(message)));
    }

    @Override
    public Object updateMessageStatus(MessageVo message) {
        message.setCtime(null);
        if(message.getMessageId() == null){
            Message messageQu = new Message();
            messageQu.setRid(message.getRid());
            messageQu.setUserId(message.getUserId());
            messageQu.setType(message.getType());
            messageQu.setStatus(0);
            messageQu.setCtime(null);
            List<Message> messages = messageMapper.select(messageQu);
            if(messages.size() > 0){
                messageQu = messages.get(0);
                messageQu.setStatus(1);
                return messageMapper.updateByPrimaryKeySelective(messageQu);
            }else{
                throw new BusinessException("修改消息状态失败！");
            }
        }else{
            return messageMapper.updateByPrimaryKeySelective(message);
        }
    }

    @Override
    public Object updateMyAllMessageStatus(MessageVo message) {
        return messageMapper.updateBatchStatus(message);
    }

    @Override
    public Object queryVersion(Integer versionCode,Integer type) {
        Version version = versionMapper.queryNewVersion(type);
        Map<String,Object> map = new HashMap<String, Object>();
        if(version != null){
            version.setDownloadUrl(downloadurl+version.getDownloadUrl());
            if(version.getVersionCode() == versionCode){
                map.put("isNew", true);
            }else{
                map.put("isNew", false);
            }
            map.put("newVersion", version);
            return map;
        }else{
            throw new BusinessException("没有版本更新！"); 
        }
    }

    @Override
    public Object saveFeedBack(Feedback feedback) {
        return feedbackMapper.insertSelective(feedback);
    }

}
