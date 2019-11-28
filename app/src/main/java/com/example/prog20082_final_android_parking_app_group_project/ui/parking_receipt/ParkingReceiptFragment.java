package com.example.prog20082_final_android_parking_app_group_project.ui.parking_receipt;

import android.app.Activity;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.example.prog20082_final_android_parking_app_group_project.parking_model.Parking;

import java.util.List;
import java.util.Locale;


public class ParkingReceiptFragment extends Fragment implements View.OnClickListener {

    View root;

    TextView tvBuildingDetails;
    TextView tvDurationDetails;
    TextView tvPlateDetails;
    TextView tvHostSuiteDetails;
    TextView tvDateDetails;
    TextView tvTimeDetails;
    TextView tvCostDetails;

    Button btnShowReceipts;

    Integer buildingCode;
    String parkingDuration;
    String carPlateNumber;
    Integer hostSuiteNumber;
    String parkingDate;
    String parkingTime;
    Double parkingCost;

    Parking parking;

    List<Parking> allParking;

    private ParkingReceiptViewModel parkingReceiptViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TransitionInflater.from(getContext()).inflateTransition(android.R.transition.fade);
        parkingReceiptViewModel = new ViewModelProvider(this, new ParkingAppViewModelFactory(getActivity().getApplication())).get(ParkingReceiptViewModel.class);
        root = inflater.inflate(R.layout.fragment_view_parking_receipt, container, false);
        final TextView textView = root.findViewById(R.id.tv_view_parking_receipt_title);
        parkingReceiptViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        this.referWidgets();

        this.displayReceipt();

        return root;
    }

    private void referWidgets() {
        tvBuildingDetails = root.findViewById(R.id.tv_building_details);
        tvDurationDetails =  root.findViewById(R.id.tv_duration_details);
        tvPlateDetails = root.findViewById(R.id.tv_plate_details);
        tvHostSuiteDetails = root.findViewById(R.id.tv_host_suite_details);
        tvDateDetails = root.findViewById(R.id.tv_date_details);
        tvTimeDetails = root.findViewById(R.id.tv_time_details);
        tvCostDetails = root.findViewById(R.id.tv_cost_details);

        btnShowReceipts = root.findViewById(R.id.btn_show_receipts);
        btnShowReceipts.setOnClickListener(this);
    }

    private void displayReceipt(){
        parkingReceiptViewModel.getAllParking().observe(getViewLifecycleOwner(), new Observer<List<Parking>>() {
            @Override
            public void onChanged(List<Parking> parkings) {
                allParking = parkings;
                parking = allParking.get(0);
                tvBuildingDetails.setText(String.format(Locale.CANADA, getString(R.string.building_code_details), parking.getBuildingCode()));
                tvDurationDetails.setText(String.format(Locale.CANADA, getString(R.string.parking_duration_details), parking.getParkingDuration()));
                tvPlateDetails.setText(String.format(Locale.CANADA, getString(R.string.car_plate_number_details), parking.getPlateNumber()));
                tvHostSuiteDetails.setText(String.format(Locale.CANADA, getString(R.string.host_suite_number_details), parking.getSuiteNumber()));
                tvDateDetails.setText(String.format(Locale.CANADA, getString(R.string.parking_date_details), parking.getParkingDate()));
                tvTimeDetails.setText(String.format(Locale.CANADA, getString(R.string.parking_time_details), parking.getParkingTime()));
                tvCostDetails.setText("$" + String.format(Locale.CANADA, getString(R.string.parking_cost_details), parking.getParkingCost()));
            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}