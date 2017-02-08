package com.karakal.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bs_version")
public class Version {
    @Id
    @Column(name = "version_id")
    private Integer versionId;

    /**
     * 版本名
     */
    @Column(name = "version_name")
    private String versionName;

    @Column(name = "download_url")
    private String downloadUrl;

    private Date ctime;

    /**
     * 版本号
     */
    @Column(name = "version_code")
    private Integer versionCode;

    /**
     * @return version_id
     */
    public Integer getVersionId() {
        return versionId;
    }

    /**
     * @param versionId
     */
    public void setVersionId(Integer versionId) {
        this.versionId = versionId;
    }

    /**
     * 获取版本名
     *
     * @return version_name - 版本名
     */
    public String getVersionName() {
        return versionName;
    }

    /**
     * 设置版本名
     *
     * @param versionName 版本名
     */
    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    /**
     * @return download_url
     */
    public String getDownloadUrl() {
        return downloadUrl;
    }

    /**
     * @param downloadUrl
     */
    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
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
     * 获取版本号
     *
     * @return version_code - 版本号
     */
    public Integer getVersionCode() {
        return versionCode;
    }

    /**
     * 设置版本号
     *
     * @param versionCode 版本号
     */
    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }
}