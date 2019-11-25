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
        parkingDao = db.ParkingDao();
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

    public void delete(Parking parking){
        new deleteAsyncTask(parkingDao).execute(parking);
    }

    private static class deleteAsyncTask extends AsyncTask<Parking, Void, Void> {
        private ParkingDao asyncTaskDao;

        deleteAsyncTask(ParkingDao parkingDao){
            asyncTaskDao = parkingDao;
        }

        @Override
        protected Void doInBackground(Parking...parkings) {
            asyncTaskDao.delete(parkings[0]);
            return null;
        }
    }

    public void deleteAllParking(){
        new deleteAllParkingAsyncTask(parkingDao).execute();
    }

    private static class deleteAllParkingAsyncTask extends AsyncTask<Void, Void, Void> {
        private ParkingDao asyncTaskDao;

        deleteAllParkingAsyncTask(ParkingDao parkingDao){
            asyncTaskDao = parkingDao;
        }

        @Override
        protected Void doInBackground(Void...voids) {
            asyncTaskDao.deleteAllParking();
            return null;
        }
    }
}
