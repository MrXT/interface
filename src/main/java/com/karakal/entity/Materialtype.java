package com.karakal.entity;

import javax.persistence.*;

@Table(name = "bs_materialtype")
public class Materialtype {
    @Id
    @Column(name = "MATERIALTYPE_ID")
    private String materialtypeId;

    /**
     * 物资类型名
     */
    @Column(name = "MATERIAL_TYPE_NAME")
    private String materialTypeName;

    /**
     * @return MATERIALTYPE_ID
     */
    public String getMaterialtypeId() {
        return materialtypeId;
    }

    /**
     * @param materialtypeId
     */
    public void setMaterialtypeId(String materialtypeId) {
        this.materialtypeId = materialtypeId;
    }

    /**
     * 获取物资类型名
     *
     * @return MATERIAL_TYPE_NAME - 物资类型名
     */
    public String getMaterialTypeName() {
        return materialTypeName;
    }

    /**
     * 设置物资类型名
     *
     * @param materialTypeName 物资类型名
     */
    public void setMaterialTypeName(String materialTypeName) {
        this.materialTypeName = materialTypeName;
    }
}