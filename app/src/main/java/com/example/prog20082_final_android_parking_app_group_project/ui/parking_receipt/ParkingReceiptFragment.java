package com.example.prog20082_final_android_parking_app_group_project.ui.parking_receipt;

import android.app.Activity;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.prog20082_final_android_parking_app_group_project.MainActivity;
import com.example.prog20082_final_android_parking_app_group_project.ParkingAppViewModelFactory;
import com.example.prog20082_final_android_parking_app_group_project.R;

public class ParkingReceiptFragment extends Fragment {

    private ParkingReceiptViewModel parkingReceiptViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TransitionInflater.from(getContext()).inflateTransition(android.R.transition.fade);
        parkingReceiptViewModel = new ViewModelProvider(this, new ParkingAppViewModelFactory(getActivity().getApplication())).get(ParkingReceiptViewModel.class);
        View root = inflater.inflate(R.layout.fragment_view_parking_receipt, container, false);
        final TextView textView = root.findViewById(R.id.tv_view_parking_receipt_title);
        parkingReceiptViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}