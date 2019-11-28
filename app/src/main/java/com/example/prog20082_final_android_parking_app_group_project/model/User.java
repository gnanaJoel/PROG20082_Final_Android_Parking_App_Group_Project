package com.example.prog20082_final_android_parking_app_group_project.model;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * PROG20082_Final_Android_Parking_App_Group_Project created by ammarkhan
 * <p>
 * studentID: 991439943
 * <p>
 * on 2019-11-26
 */

@Entity(tableName = "user_data_table")
public class User implements Serializable
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "fullname")
    private String fullName;

    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "phoneNumber")
    private String phoneNumber;

    @ColumnInfo(name = "plateNumber")
    private String plateNumber;

    @ColumnInfo(name = "cardName")
    private String cardName;

    @ColumnInfo(name = "cardNumber")
    private String cardNumber;

    @ColumnInfo(name = "expiringDate")
    private String expiringDate;

    @ColumnInfo(name = "cvvNumber")
    private String cvvNumber;

    public User(String fullName,
                String email, String password,
                String phoneNumber, String plateNumber,
                String cardName, String cardNumber,
                String expiringDate, String cvvNumber)
    {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.plateNumber = plateNumber;
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        this.expiringDate = expiringDate;
        this.cvvNumber = cvvNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getCardName() {
        return cardName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpiringDate() {
        return expiringDate;
    }

    public String getCvvNumber() {
        return cvvNumber;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExpiringDate(String expiringDate) {
        this.expiringDate = expiringDate;
    }

    public void setCvvNumber(String cvvNumber) {
        this.cvvNumber = cvvNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", plateNumber='" + plateNumber + '\'' +
                ", cardName='" + cardName + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", expiringDate='" + expiringDate + '\'' +
                ", cvvNumber='" + cvvNumber + '\'' +
                '}';
    }
}
