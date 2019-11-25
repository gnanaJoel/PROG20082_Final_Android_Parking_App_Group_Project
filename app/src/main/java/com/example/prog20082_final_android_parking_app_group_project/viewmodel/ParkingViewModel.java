package com.example.prog20082_final_android_parking_app_group_project.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.prog20082_final_android_parking_app_group_project.db.ParkingRepository;
import com.example.prog20082_final_android_parking_app_group_project.parking_model.Parking;

import java.util.List;

/**
 * PROG20082_Final_Android_Parking_App_Group_Project created by joelgnanasekaram
 * student_id: 991518913
 * on 2019-11-25
 */
public class ParkingViewModel extends AndroidViewModel {
    private LiveData<List<Parking>> allParking;
    private ParkingRepository parkingRepository;


    public ParkingViewModel(@NonNull Application application) {
        super(application);
        parkingRepository = new ParkingRepository(application);
        allParking = parkingRepository.getAllParking();
    }

    public void insert(Parking parking){
        parkingRepository.insert(parking);
    }

    public LiveData<List<Parking>> getAllParking(){
        return allParking;
    }

    public void delete(Parking parking){
        parkingRepository.delete(parking);
    }

    public void deleteAllFruits(){
        parkingRepository.deleteAllParking();
    }
}
