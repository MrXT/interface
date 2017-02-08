package com.karakal.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.karakal.controller.system.vo.UserVo;
import com.karakal.entity.User;
import com.karakal.util.MyMapper;

public interface UserMapper extends MyMapper<User> {

    List<UserVo> selectDetail(User userVO);

    List<UserVo> queryUserByDId(UserVo userVo);

    List<String> queryQuiet(@Param("ids")String join);
}