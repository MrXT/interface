package com.karakal.entity;

import javax.persistence.*;

@Table(name = "sys_user")
public class User {
    @Id
    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "NAME")
    private String name;

    @Column(name = "RIGHTS")
    private String rights;

    @Column(name = "ROLE_ID")
    private String roleId;

    @Column(name = "LAST_LOGIN")
    private String lastLogin;

    @Column(name = "IP")
    private String ip;

    @Column(name = "STATUS")
    private String status;
    @Column(name = "head_pic")
    private String headPic;
    
    private Integer quiet;
    
    public Integer getQuiet() {
        return quiet;
    }
    
    public void setQuiet(Integer quiet) {
        this.quiet = quiet;
    }

    private String position;//职称
    
    
    public String getPosition() {
        return position;
    }

    
    public void setPosition(String position) {
        this.position = position;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    @Column(name = "SIGN")
    private String sign;
    public String getSign() {
        return sign;
    }

    
    public void setSign(String sign) {
        this.sign = sign;
    }

    @Column(name = "BZ")
    private String bz;

    @Column(name = "SKIN")
    private String skin;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "NUMBER")
    private String number;

    @Column(name = "PHONE")
    private String phone;

    /**
     * 办公室门牌号
     */
    @Column(name = "OFFICE_NO")
    private String officeNo;

    /**
     * 座机号码
     */
    @Column(name = "CPHONE")
    private String cphone;

    /**
     * 所属区域
     */
    @Column(name = "AREA")
    private String area;

    /**
     * 所属楼层
     */
    @Column(name = "FLOOR")
    private String floor;

    /**
     * 推送客户端ID
     */
    @Column(name = "REGISTER_ID")
    private String registerId;

    /**
     * 设备类型： 1：andrior  2：ios
     */
    @Column(name = "DEVICE_TYPE")
    private Integer deviceType;

    /**
     * 是否公布
     */
    @Column(name = "ISPUBLISH")
    private Byte ispublish;

    /**
     * @return USER_ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return USERNAME
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return PASSWORD
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return NAME
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return RIGHTS
     */
    public String getRights() {
        return rights;
    }

    /**
     * @param rights
     */
    public void setRights(String rights) {
        this.rights = rights;
    }

    /**
     * @return ROLE_ID
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * @return LAST_LOGIN
     */
    public String getLastLogin() {
        return lastLogin;
    }

    /**
     * @param lastLogin
     */
    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    /**
     * @return IP
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return STATUS
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return BZ
     */
    public String getBz() {
        return bz;
    }

    /**
     * @param bz
     */
    public void setBz(String bz) {
        this.bz = bz;
    }

    /**
     * @return SKIN
     */
    public String getSkin() {
        return skin;
    }

    /**
     * @param skin
     */
    public void setSkin(String skin) {
        this.skin = skin;
    }

    /**
     * @return EMAIL
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return NUMBER
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * @return PHONE
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取办公室门牌号
     *
     * @return OFFICE_NO - 办公室门牌号
     */
    public String getOfficeNo() {
        return officeNo;
    }

    /**
     * 设置办公室门牌号
     *
     * @param officeNo 办公室门牌号
     */
    public void setOfficeNo(String officeNo) {
        this.officeNo = officeNo;
    }

    /**
     * 获取座机号码
     *
     * @return CPHONE - 座机号码
     */
    public String getCphone() {
        return cphone;
    }

    /**
     * 设置座机号码
     *
     * @param cphone 座机号码
     */
    public void setCphone(String cphone) {
        this.cphone = cphone;
    }

    /**
     * 获取所属区域
     *
     * @return AREA - 所属区域
     */
    public String getArea() {
        return area;
    }

    /**
     * 设置所属区域
     *
     * @param area 所属区域
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * 获取所属楼层
     *
     * @return FLOOR - 所属楼层
     */
    public String getFloor() {
        return floor;
    }

    /**
     * 设置所属楼层
     *
     * @param floor 所属楼层
     */
    public void setFloor(String floor) {
        this.floor = floor;
    }

    /**
     * 获取推送客户端ID
     *
     * @return REGISTER_ID - 推送客户端ID
     */
    public String getRegisterId() {
        return registerId;
    }

    /**
     * 设置推送客户端ID
     *
     * @param registerId 推送客户端ID
     */
    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }

    /**
     * 获取设备类型： 1：andrior  2：ios
     *
     * @return DEVICE_TYPE - 设备类型： 1：andrior  2：ios
     */
    public Integer getDeviceType() {
        return deviceType;
    }

    /**
     * 设置设备类型： 1：andrior  2：ios
     *
     * @param deviceType 设备类型： 1：andrior  2：ios
     */
    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    /**
     * 获取是否公布
     *
     * @return ISPUBLISH - 是否公布
     */
    public Byte getIspublish() {
        return ispublish;
    }

    /**
     * 设置是否公布
     *
     * @param ispublish 是否公布
     */
    public void setIspublish(Byte ispublish) {
        this.ispublish = ispublish;
    }
}