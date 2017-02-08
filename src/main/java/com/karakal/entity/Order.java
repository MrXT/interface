package com.karakal.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bs_order")
public class Order {
    @Id
    @Column(name = "order_id")
    private String orderId;

    /**
     * 工单描述
     */
    private String infor;

    /**
     * 工单涉及到的物资名
     */
    @Column(name = "material_names")
    private String materialNames;

    /**
     * 工单创建时间
     */
    private Date ctime;

    /**
     * 申请人ID
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 服务类型ID
     */
    @Column(name = "servicetype_id")
    private String servicetypeId;

    /**
     * 处理结果
     */
    @Column(name = "handler_info")
    private String handlerInfo;

    /**
     * 物资名ids
     */
    @Column(name = "materialname_ids")
    private String materialnameIds;

    /**
     * @return order_id
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取工单描述
     *
     * @return infor - 工单描述
     */
    public String getInfor() {
        return infor;
    }

    /**
     * 设置工单描述
     *
     * @param infor 工单描述
     */
    public void setInfor(String infor) {
        this.infor = infor;
    }

    /**
     * 获取工单涉及到的物资名
     *
     * @return material_names - 工单涉及到的物资名
     */
    public String getMaterialNames() {
        return materialNames;
    }

    /**
     * 设置工单涉及到的物资名
     *
     * @param materialNames 工单涉及到的物资名
     */
    public void setMaterialNames(String materialNames) {
        this.materialNames = materialNames;
    }

    /**
     * 获取工单创建时间
     *
     * @return ctime - 工单创建时间
     */
    public Date getCtime() {
        return ctime;
    }

    /**
     * 设置工单创建时间
     *
     * @param ctime 工单创建时间
     */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /**
     * 获取申请人ID
     *
     * @return user_id - 申请人ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置申请人ID
     *
     * @param userId 申请人ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取服务类型ID
     *
     * @return servicetype_id - 服务类型ID
     */
    public String getServicetypeId() {
        return servicetypeId;
    }

    /**
     * 设置服务类型ID
     *
     * @param servicetypeId 服务类型ID
     */
    public void setServicetypeId(String servicetypeId) {
        this.servicetypeId = servicetypeId;
    }

    /**
     * 获取处理结果
     *
     * @return handler_info - 处理结果
     */
    public String getHandlerInfo() {
        return handlerInfo;
    }

    /**
     * 设置处理结果
     *
     * @param handlerInfo 处理结果
     */
    public void setHandlerInfo(String handlerInfo) {
        this.handlerInfo = handlerInfo;
    }

    /**
     * 获取物资名ids
     *
     * @return materialname_ids - 物资名ids
     */
    public String getMaterialnameIds() {
        return materialnameIds;
    }

    /**
     * 设置物资名ids
     *
     * @param materialnameIds 物资名ids
     */
    public void setMaterialnameIds(String materialnameIds) {
        this.materialnameIds = materialnameIds;
    }
}