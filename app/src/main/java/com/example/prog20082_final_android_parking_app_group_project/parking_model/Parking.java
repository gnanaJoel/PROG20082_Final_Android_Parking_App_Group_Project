package com.example.prog20082_final_android_parking_app_group_project.parking_model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import java.io.Serializable;

/**
 * PROG20082_Final_Android_Parking_App_Group_Project created by joelgnanasekaram
 * student_id: 991518913
 * on 2019-11-21
 */
@Entity(tableName = "parking_table")
public class Parking implements Serializable {
    @ColumnInfo(name = "building_code")
    private Integer buildingCode;

    @ColumnInfo(name = "parking_duration")
    private Float hoursAmount;

    @ColumnInfo(name = "plate_number")
    private String plateNumber;

    @ColumnInfo(name = "suite_number")
    private Integer suiteNumber;

    public Parking() {

    }

    public Parking(Integer buildingCode, Float hours, String plateNumber, Integer suitNumber) {
        this.buildingCode = buildingCode;
        this.hoursAmount = hours;
        this.plateNumber = plateNumber;
        this.suiteNumber = suitNumber;
    }

    public Integer getBuildingCode() {
        return buildingCode;
    }

    public void setBuildingCode(Integer buildingCode) {
        this.buildingCode = buildingCode;
    }

    public Float getHoursAmount() {
        return hoursAmount;
    }

    public void setHoursAmount(Float hoursAmount) {
        this.hoursAmount = hoursAmount;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Integer getSuiteNumber() {
        return suiteNumber;
    }

    public void setSuiteNumber(Integer suiteNumber) {
        this.suiteNumber = suiteNumber;
    }

    @Override
    public String toString() {
        return "Parking{" +
                "buildingCode=" + buildingCode +
                ", hoursAmount=" + hoursAmount +
                ", plateNumber='" + plateNumber + '\'' +
                ", suitNumber=" + suiteNumber +
                '}';
    }
}
