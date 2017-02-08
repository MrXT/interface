package com.karakal.controller.system;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.karakal.bean.ResponseJson;
import com.karakal.constant.SystemConstant;
import com.karakal.controller.system.vo.UserVo;
import com.karakal.exception.BusinessException;
import com.karakal.service.system.UserService;

/**
 * mzk错误码处理 ClassName: ApiController <br/>
 * @author xt(di.xiao@karakal.com.cn)
 * @date 2016年8月3日
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Resource
    private Map<String, String> statusMap;
    @Autowired
    public UserService userService;
    /**
     * 登录
     * @param user
     * @return
     */
    @RequestMapping("login")
    public Object login(UserVo user,HttpSession session){
        if(StringUtils.isBlank(user.getPhone())&&StringUtils.isBlank(user.getPassword())){
            throw new BusinessException(statusMap.get(SystemConstant.NULL_MUSTPARAMETER));
        }
        Object object = userService.login(user);
        session.setAttribute("user", object);
        return object;
    }
    /**
     * 注册与修改
     * @param user
     * @return
     */
    @RequestMapping(value="save",method = RequestMethod.POST)
    public Object save(UserVo user,@RequestParam(defaultValue="0")int type,HttpSession session){
        if(type == 2 && StringUtils.isBlank(user.getUserId())){
            throw new BusinessException(statusMap.get(SystemConstant.NULL_MUSTPARAMETER));
        }
        if(type == 2){
            return userService.save(user);
        }
        if(session.getAttribute("code") == null ){
            throw new BusinessException("验证码失效！");
        }
        if(!session.getAttribute("code").toString().equals(user.getCode())){
            throw new BusinessException("验证码错误！");
        }
        if(type == 0 && (StringUtils.isBlank(user.getPhone()) ||StringUtils.isBlank(user.getName())||StringUtils.isBlank(user.getDepartmentId()))){
            throw new BusinessException(statusMap.get(SystemConstant.NULL_MUSTPARAMETER));
        }
        if(type == 0 && !com.karakal.util.StringUtil.isMobileNo(user.getPhone())){
            throw new BusinessException("手机号码格式不正确！");
        }
        if(type == 1 && StringUtils.isBlank(user.getPassword())){
            throw new BusinessException(statusMap.get(SystemConstant.NULL_MUSTPARAMETER));
        }
        if(type == 1){
            return userService.saveUser(user);
        }
        if(type == 3&& StringUtils.isBlank(user.getPhone())){
            throw new BusinessException(statusMap.get(SystemConstant.NULL_MUSTPARAMETER));
        }
        if(user.getUserId() != null){
            return userService.save(user);
        }
        ResponseJson json = new ResponseJson();
        json.setStatus(1);
        json.setMsg("注册成功，等待管理员激活中！");
        json.setData(userService.save(user));
        return json;
    }
}
