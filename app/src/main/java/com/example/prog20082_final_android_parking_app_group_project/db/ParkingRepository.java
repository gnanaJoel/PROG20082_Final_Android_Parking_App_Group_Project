package com.example.prog20082_final_android_parking_app_group_project.db;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.prog20082_final_android_parking_app_group_project.parking_model.Parking;

import java.util.List;

/**
 * PROG20082_Final_Android_Parking_App_Group_Project created by joelgnanasekaram
 * student_id: 991518913
 * on 2019-11-25
 */
public class ParkingRepository {
    private ParkingDao parkingDao;
    private LiveData<List<Parking>> allParking;

    public ParkingRepository(Application application){
        AppDB db = AppDB.getINSTANCE(application);
        parkingDao = db.parkingDao();
        allParking = parkingDao.getAllParking();
    }

    public LiveData<List<Parking>> getAllParking() {
        return allParking;
    }

    public void insert(Parking parking){
        new insertAsyncTask(parkingDao).execute(parking);
    }

    private static class insertAsyncTask extends AsyncTask<Parking, Void, Void> {
        private ParkingDao asyncTaskDao;

        insertAsyncTask(ParkingDao parkingDao){
            asyncTaskDao = parkingDao;
        }

        @Override
        protected Void doInBackground(Parking...parkings) {
            asyncTaskDao.insert(parkings[0]);
            return null;
        }
    }
}
