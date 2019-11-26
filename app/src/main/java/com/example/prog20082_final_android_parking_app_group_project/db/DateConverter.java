package com.example.prog20082_final_android_parking_app_group_project.db;

import androidx.room.TypeConverter;

import java.util.Date;

/**
 * PROG20082_Final_Android_Parking_App_Group_Project created by joelgnanasekaram
 * student_id: 991518913
 * on 2019-11-25
 */
public class DateConverter {
    @TypeConverter
    public static Date toDate(Long dateLong){
        return dateLong == null? null : new Date(dateLong);
    }

    @TypeConverter
    public static Long fromDate(Date date){
        return date == null? null : date.getTime();
    }
}
