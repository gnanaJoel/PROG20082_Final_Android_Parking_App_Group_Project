package com.example.prog20082_final_android_parking_app_group_project.db;

import com.example.prog20082_final_android_parking_app_group_project.model.User;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

/**
 * PROG20082_Final_Android_Parking_App_Group_Project created by ammarkhan
 * <p>
 * studentID: 991439943
 * <p>
 * on 2019-11-26
 */

@Dao
public interface UserDao
{
    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("DELETE FROM user_data_table WHERE email = :email")
    void deleteUserByEmail(String email);

    @Query("SELECT * FROM user_data_table ORDER BY id ASC")
    LiveData<List<User>> getAllUsers();
}
