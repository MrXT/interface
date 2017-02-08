package com.karakal.entity;

import javax.persistence.*;

@Table(name = "bs_department_station_user")
public class DepartmentStationUser {
    @Id
    @Column(name = "department_station_user_id")
    private String departmentStationUserId;

    @Column(name = "department_id")
    private String departmentId;

    @Column(name = "station_id")
    private String stationId;

    @Column(name = "user_id")
    private String userId;

    /**
     * @return department_station_user_id
     */
    public String getDepartmentStationUserId() {
        return departmentStationUserId;
    }

    /**
     * @param departmentStationUserId
     */
    public void setDepartmentStationUserId(String departmentStationUserId) {
        this.departmentStationUserId = departmentStationUserId;
    }

    /**
     * @return department_id
     */
    public String getDepartmentId() {
        return departmentId;
    }

    /**
     * @param departmentId
     */
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * @return station_id
     */
    public String getStationId() {
        return stationId;
    }

    /**
     * @param stationId
     */
    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    /**
     * @return user_id
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
}