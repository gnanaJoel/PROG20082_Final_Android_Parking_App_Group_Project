package com.example.prog20082_final_android_parking_app_group_project.ui.parking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.prog20082_final_android_parking_app_group_project.R;

public class ParkingFragment extends Fragment implements View.OnClickListener {

    EditText edtBuilding;
    EditText edtPlate;
    EditText edtHostSuit;

    RadioGroup rdgHours;
    RadioButton rdoHoursSelected;

    Button btnCancel;
    Button btnParkCar;

    private ParkingViewModel parkingViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        parkingViewModel =
                ViewModelProviders.of(this).get(ParkingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add_parking, container, false);
        final TextView textView = root.findViewById(R.id.tv_add_parking_title);
        parkingViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        this.referWidgets(root);

        return root;
    }

    void referWidgets(View root){
        edtBuilding = root.findViewById(R.id.edt_building);
        edtPlate = root.findViewById(R.id.edt_plate);
        edtHostSuit = root.findViewById(R.id.edt_host_suit);

        rdgHours = root.findViewById(R.id.rdg_hours);

        btnCancel = root.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(this);

        btnParkCar = root.findViewById(R.id.btn_park_car);
        btnParkCar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_cancel:
            case R.id.btn_park_car:
        }
    }


}