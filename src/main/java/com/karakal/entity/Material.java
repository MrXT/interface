package com.karakal.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bs_material")
public class Material {
    @Id
    @Column(name = "material_id")
    private String materialId;

    /**
     * 物资类型名
     */
    @Column(name = "materialtype_id")
    private String materialtypeId;

    /**
     * 物资名称ID
     */
    @Column(name = "materialname_id")
    private String materialnameId;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 产品型号
     */
    private String model;

    /**
     * 产品参数
     */
    private String param;

    /**
     * 产品序列号
     */
    @Column(name = "serial_number")
    private String serialNumber;

    /**
     * 产品价格
     */
    private Long price;

    /**
     * 存放办公区
     */
    @Column(name = "deposit_office")
    private String depositOffice;

    /**
     * 存放楼层
     */
    @Column(name = "deposit_floor")
    private String depositFloor;

    /**
     * 存放房间号
     */
    @Column(name = "deposit_house")
    private String depositHouse;

    private String remark;

    /**
     * 0:新设备
1:旧设备
2：报损
3：报废
4：已发放
5：锁定
6:补录
     */
    private Integer status;

    private Integer count;
    
    private Integer isNeedSerial;
    
    

    @Column(name = "is_need_serial")
    public Integer getIsNeedSerial() {
        return isNeedSerial;
    }

    
    public void setIsNeedSerial(Integer isNeedSerial) {
        this.isNeedSerial = isNeedSerial;
    }

    private Date ctime;

    /**
     * 正在使用的员工ID
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * @return material_id
     */
    public String getMaterialId() {
        return materialId;
    }

    /**
     * @param materialId
     */
    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    /**
     * 获取物资类型名
     *
     * @return materialtype_id - 物资类型名
     */
    public String getMaterialtypeId() {
        return materialtypeId;
    }

    /**
     * 设置物资类型名
     *
     * @param materialtypeId 物资类型名
     */
    public void setMaterialtypeId(String materialtypeId) {
        this.materialtypeId = materialtypeId;
    }

    /**
     * 获取物资名称ID
     *
     * @return materialname_id - 物资名称ID
     */
    public String getMaterialnameId() {
        return materialnameId;
    }

    /**
     * 设置物资名称ID
     *
     * @param materialnameId 物资名称ID
     */
    public void setMaterialnameId(String materialnameId) {
        this.materialnameId = materialnameId;
    }

    /**
     * 获取品牌
     *
     * @return brand - 品牌
     */
    public String getBrand() {
        return brand;
    }

    /**
     * 设置品牌
     *
     * @param brand 品牌
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * 获取产品型号
     *
     * @return model - 产品型号
     */
    public String getModel() {
        return model;
    }

    /**
     * 设置产品型号
     *
     * @param model 产品型号
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * 获取产品参数
     *
     * @return param - 产品参数
     */
    public String getParam() {
        return param;
    }

    /**
     * 设置产品参数
     *
     * @param param 产品参数
     */
    public void setParam(String param) {
        this.param = param;
    }

    /**
     * 获取产品序列号
     *
     * @return serial_number - 产品序列号
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * 设置产品序列号
     *
     * @param serialNumber 产品序列号
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * 获取产品价格
     *
     * @return price - 产品价格
     */
    public Long getPrice() {
        return price;
    }

    /**
     * 设置产品价格
     *
     * @param price 产品价格
     */
    public void setPrice(Long price) {
        this.price = price;
    }

    /**
     * 获取存放办公区
     *
     * @return deposit_office - 存放办公区
     */
    public String getDepositOffice() {
        return depositOffice;
    }

    /**
     * 设置存放办公区
     *
     * @param depositOffice 存放办公区
     */
    public void setDepositOffice(String depositOffice) {
        this.depositOffice = depositOffice;
    }

    /**
     * 获取存放楼层
     *
     * @return deposit_floor - 存放楼层
     */
    public String getDepositFloor() {
        return depositFloor;
    }

    /**
     * 设置存放楼层
     *
     * @param depositFloor 存放楼层
     */
    public void setDepositFloor(String depositFloor) {
        this.depositFloor = depositFloor;
    }

    /**
     * 获取存放房间号
     *
     * @return deposit_house - 存放房间号
     */
    public String getDepositHouse() {
        return depositHouse;
    }

    /**
     * 设置存放房间号
     *
     * @param depositHouse 存放房间号
     */
    public void setDepositHouse(String depositHouse) {
        this.depositHouse = depositHouse;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取0:新设备
1:旧设备
2：报损
3：报废
4：已发放
5：锁定
6:补录
     *
     * @return status - 0:新设备
1:旧设备
2：报损
3：报废
4：已发放
5：锁定
6:补录
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置0:新设备
1:旧设备
2：报损
3：报废
4：已发放
5：锁定
6:补录
     *
     * @param status 0:新设备
1:旧设备
2：报损
3：报废
4：已发放
5：锁定
6:补录
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return count
     */
    public Integer getCount() {
        return count;
    }

    /**
     * @param count
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * @return ctime
     */
    public Date getCtime() {
        return ctime;
    }

    /**
     * @param ctime
     */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /**
     * 获取正在使用的员工ID
     *
     * @return user_id - 正在使用的员工ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置正在使用的员工ID
     *
     * @param userId 正在使用的员工ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
}