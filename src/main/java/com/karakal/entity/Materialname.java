package com.karakal.entity;

import javax.persistence.*;

@Table(name = "bs_materialname")
public class Materialname {
    @Id
    @Column(name = "MATERIALNAME_ID")
    private String materialnameId;

    /**
     * 物资名称
     */
    @Column(name = "MATERIAL_NAME")
    private String materialName;

    /**
     * 物资类型名
     */
    @Column(name = "MATERIAL_TYPE_ID")
    private String materialTypeId;

    /**
     * @return MATERIALNAME_ID
     */
    public String getMaterialnameId() {
        return materialnameId;
    }

    /**
     * @param materialnameId
     */
    public void setMaterialnameId(String materialnameId) {
        this.materialnameId = materialnameId;
    }

    /**
     * 获取物资名称
     *
     * @return MATERIAL_NAME - 物资名称
     */
    public String getMaterialName() {
        return materialName;
    }

    /**
     * 设置物资名称
     *
     * @param materialName 物资名称
     */
    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    /**
     * 获取物资类型名
     *
     * @return MATERIAL_TYPE_ID - 物资类型名
     */
    public String getMaterialTypeId() {
        return materialTypeId;
    }

    /**
     * 设置物资类型名
     *
     * @param materialTypeId 物资类型名
     */
    public void setMaterialTypeId(String materialTypeId) {
        this.materialTypeId = materialTypeId;
    }
}