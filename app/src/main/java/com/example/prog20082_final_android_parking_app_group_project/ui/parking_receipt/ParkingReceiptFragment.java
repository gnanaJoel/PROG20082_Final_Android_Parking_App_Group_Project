package com.example.prog20082_final_android_parking_app_group_project.ui.parking_receipt;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.prog20082_final_android_parking_app_group_project.R;

public class ParkingReceiptFragment extends Fragment {

    private ParkingReceiptViewModel mViewModel;

    public static ParkingReceiptFragment newInstance() {
        return new ParkingReceiptFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view_parking_receipt, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ParkingReceiptViewModel.class);
    }

}
