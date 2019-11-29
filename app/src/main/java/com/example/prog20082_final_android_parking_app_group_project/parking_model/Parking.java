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
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "parking_id")
    private int parkingId;

    @ColumnInfo(name = "building_code")
    private Integer buildingCode;

    @ColumnInfo(name = "parking_duration")
    private String parkingDuration;

    @ColumnInfo(name = "plate_number")
    private String plateNumber;

    @ColumnInfo(name = "suite_number")
    private Integer suiteNumber;

    @ColumnInfo(name = "parking_date")
    private String parkingDate;

    @ColumnInfo(name = "parking_time")
    private String parkingTime;

    @ColumnInfo(name = "parking_cost")
    private Double parkingCost;

    public Parking() {

    }

    public Parking(Integer buildingCode, String parkingDuration, String plateNumber, Integer suitNumber) {
        this.buildingCode = buildingCode;
        this.parkingDuration = parkingDuration;
        this.plateNumber = plateNumber;
        this.suiteNumber = suitNumber;
        this.parkingDate = "";
        this.parkingTime = "";
        this.parkingCost = 0.0;
    }

    public Integer getBuildingCode() {
        return buildingCode;
    }

    public void setBuildingCode(Integer buildingCode) {
        this.buildingCode = buildingCode;
    }

    public String getParkingDuration() {
        return parkingDuration;
    }

    public void setParkingDuration(String parkingDuration) {
        this.parkingDuration = parkingDuration;
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

    public String getParkingDate() {
        return parkingDate;
    }

    public void setParkingDate(String parkingDate) {
        this.parkingDate = parkingDate;
    }

    public String getParkingTime() {
        return parkingTime;
    }

    public void setParkingTime(String parkingTime) {
        this.parkingTime = parkingTime;
    }

    public Double getParkingCost() {
        return parkingCost;
    }

    public void setParkingCost(Double parkingCost) {
        this.parkingCost = parkingCost;
    }

    public int getParkingId() {
        return parkingId;
    }

    public void setParkingId(int parkingId) {
        this.parkingId = parkingId;
    }

    public void calculateParkingCost(){
        if(parkingDuration.equals("1 hour or less")){
            parkingCost = 4.00;
        }
        else if(parkingDuration.equals("3 hours")){
            parkingCost = 8.00;
        }
        else if(parkingDuration.equals("10 hours")){
            parkingCost = 12.00;
        }
        else if(parkingDuration.equals("24 hours (I'v got all day!)")){
            parkingCost = 20.00;
        }
    }

    @Override
    public String toString() {
        return "Parking{" +
                "parkingId=" + parkingId +
                ", buildingCode=" + buildingCode +
                ", parkingDuration='" + parkingDuration + '\'' +
                ", plateNumber='" + plateNumber + '\'' +
                ", suiteNumber=" + suiteNumber +
                ", parkingDate='" + parkingDate + '\'' +
                ", parkingTime='" + parkingTime + '\'' +
                ", parkingCost=" + parkingCost +
                '}';
    }
}
