package com.example.prog20082_final_android_parking_app_group_project.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.prog20082_final_android_parking_app_group_project.parking_model.Parking;

import java.util.List;

/**
 * PROG20082_Final_Android_Parking_App_Group_Project created by joelgnanasekaram
 * student_id: 991518913
 * on 2019-11-21
 */
@Dao
public interface ParkingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Parking parking);

    @Delete
    void delete(Parking parking);

    @Query("SELECT * FROM parking_table ORDER BY building_code ASC")
    LiveData<List<Parking>> getAllParking();

    @Query("DELETE FROM parking_table")
    void deleteAllParking();
}
