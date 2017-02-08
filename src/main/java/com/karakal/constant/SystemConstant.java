
package com.karakal.constant;

/**
 * 系统常量
 * ClassName: SystemConstant <br/>
 * Package Name:com.karakal.constant
 * @author xt(di.xiao@karakal.com.cn)
 * @version 1.0
 * Date:2016年7月28日下午2:27:37
 * Copyright (c) 2016, manzz.com All Rights Reserved.
 */
public interface SystemConstant {
    /**
     * 成功1
     */
    public final static int SUCCESS = 1;
    /**
     * 失败0
     */
    public final static int FAIL = 0;
    /**
     * Cookie中kuid不能为空！
     */
    public final static String NULL_KUID = "100010";
    /**
     * 上传文件不能为空！
     */
    public final static String NULL_FILE = "100011";
    /**
     * 必要的参数为空200001
     */
    public final static String NULL_MUSTPARAMETER = "200001";
    /**
     * 错误的参数200002
     */
    public final static String ERROR_PARAMETER = "200002";
    /**
     * 系统繁忙100002
     */
    public final static String SYSTEM_BUSY = "100002";
    /**
     * 数据库错误100005
     */
    public final static String DATABASE_ERROR = "100005";
    /**
     * 重复请求100007
     */
    public final static String REQUEST_MORE_MORE = "100007";
    /**
     * 服务器异常10008
     */
    public final static String SERVER_EXCEPTION = "100008";
    /**
     * 系统错误100009
     */
    public final static String SYSTEM_ERROR = "100009";
    /**
     * 404 请求未找到
     */
    public final static String NOT_FOUND = "404";
    /**
     * 400 坏的请求
     */
    public final static String BAD_REQUEST = "400";
    /**
     * 405 请求方式错误
     */
    public final static String METHOD_NOT_ALLOWED = "405";
    /**
     * 歌曲自定义标签表
     */
    public final static String SONG_TAG_EXT = "m_song_tag_ext";
    /**
     * 专辑自定义标签
     */
    public final static String ALBUM_TAG_EXT = "m_album_tag_ext";
    
    /**
     * 版权标签自定义表，mysql融入到karakal_copyright
     */
    public final static String KARAKAL_COPYRIGHT = "karakal_copyright";
    
    public static final String CHANNEL_EXPIRED = "__keyevent@0__:expired";
    public static final String REDIS_KEY_MEETING = "meeting";
    
}

