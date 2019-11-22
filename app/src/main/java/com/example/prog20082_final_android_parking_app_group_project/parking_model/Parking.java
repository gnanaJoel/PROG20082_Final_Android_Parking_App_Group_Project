package com.example.prog20082_final_android_parking_app_group_project.parking_model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/**
 * PROG20082_Final_Android_Parking_App_Group_Project created by joelgnanasekaram
 * student_id: 991518913
 * on 2019-11-21
 */
@Entity(tableName = "parking_table")
public class Parking implements Serializable {
    @PrimaryKey
    @ColumnInfo(name = "building_code")
    private int buildingCode;

    @ColumnInfo(name = "parking_duration")
    private float hours;

    @ColumnInfo(name = "plate_number")
    private String plateNumber;

    @ColumnInfo(name = "suite_number")
    private int suiteNumber;

    public Parking() {

    }

    public Parking(int buildingCode, int hours, String plateNumber, int suitNumber) {
        this.buildingCode = buildingCode;
        this.hours = hours;
        this.plateNumber = plateNumber;
        this.suiteNumber = suitNumber;
    }

    public int getBuildingCode() {
        return buildingCode;
    }

    public void setBuildingCode(int buildingCode) {
        this.buildingCode = buildingCode;
    }

    public float getHours() {
        return hours;
    }

    public void setHours(float hours) {
        this.hours = hours;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public int getSuiteNumber() {
        return suiteNumber;
    }

    public void setSuiteNumber(int suiteNumber) {
        this.suiteNumber = suiteNumber;
    }

    @Override
    public String toString() {
        return "Parking{" +
                "buildingCode=" + buildingCode +
                ", hours=" + hours +
                ", plateNumber='" + plateNumber + '\'' +
                ", suitNumber=" + suiteNumber +
                '}';
    }
}
