package com.karakal.dao.mapper;

import org.apache.ibatis.annotations.Param;

import com.karakal.entity.Version;
import com.karakal.util.MyMapper;

public interface VersionMapper extends MyMapper<Version> {

    Version queryNewVersion(@Param("type")Integer type);
}