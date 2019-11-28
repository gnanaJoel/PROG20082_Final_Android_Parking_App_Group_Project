package com.example.prog20082_final_android_parking_app_group_project.db;

import android.content.Context;

import com.example.prog20082_final_android_parking_app_group_project.model.User;
import com.example.prog20082_final_android_parking_app_group_project.parking_model.Parking;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * PROG20082_Final_Android_Parking_App_Group_Project created by ammarkhan
 * <p>
 * studentID: 991439943
 * <p>
 * on 2019-11-26
 */
@Database(entities = {User.class, Parking.class}, version = 2)

public abstract class AppDB extends RoomDatabase
{
    public abstract UserDao userDao();

    public abstract ParkingDao parkingDao();

    private static volatile AppDB INSTANCE;

    public static AppDB getINSTANCE(final Context context)
    {
        if(INSTANCE == null)
        {
            synchronized (AppDB.class)
            {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        AppDB.class,
                        "parking_database").fallbackToDestructiveMigration().build();
            }
        }
        return INSTANCE;
    }
}
