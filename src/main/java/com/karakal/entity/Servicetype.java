package com.karakal.entity;

import javax.persistence.*;

@Table(name = "bs_servicetype")
public class Servicetype {
    /**
     * 服务类型ID
     */
    @Id
    @Column(name = "servicetype_id")
    private String servicetypeId;

    @Column(name = "servicetype_name")
    private String servicetypeName;

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
     * @return servicetype_name
     */
    public String getServicetypeName() {
        return servicetypeName;
    }

    /**
     * @param servicetypeName
     */
    public void setServicetypeName(String servicetypeName) {
        this.servicetypeName = servicetypeName;
    }
}