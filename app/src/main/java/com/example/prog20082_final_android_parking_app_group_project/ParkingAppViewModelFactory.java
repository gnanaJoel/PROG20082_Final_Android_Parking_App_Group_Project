package com.example.prog20082_final_android_parking_app_group_project;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.prog20082_final_android_parking_app_group_project.ui.parking.ParkingViewModel;
import com.example.prog20082_final_android_parking_app_group_project.ui.parking_receipt.ParkingReceiptViewModel;

/**
 * PROG20082_Final_Android_Parking_App_Group_Project created by joelgnanasekaram
 * student_id: 991518913
 * on 2019-11-26
 */
public class ParkingAppViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    @NonNull
    private final Application application;

    public ParkingAppViewModelFactory(@NonNull Application application){
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == ParkingViewModel.class){
            return (T) new ParkingViewModel(application);
        }
        if (modelClass == ParkingReceiptViewModel.class){
            return (T) new ParkingReceiptViewModel(application);
        }
        return null;
    }
}
