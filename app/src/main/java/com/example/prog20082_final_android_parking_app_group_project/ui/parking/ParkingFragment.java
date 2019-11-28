package com.example.prog20082_final_android_parking_app_group_project.ui.parking;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.drm.DrmStore;
import android.os.Bundle;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavAction;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.prog20082_final_android_parking_app_group_project.MainActivity;
import com.example.prog20082_final_android_parking_app_group_project.ParkingAppViewModelFactory;
import com.example.prog20082_final_android_parking_app_group_project.R;
import com.example.prog20082_final_android_parking_app_group_project.model.User;
import com.example.prog20082_final_android_parking_app_group_project.parking_model.Parking;
import com.example.prog20082_final_android_parking_app_group_project.ui.parking_receipt.ParkingReceiptFragment;
import com.example.prog20082_final_android_parking_app_group_project.viewmodel.UserViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class ParkingFragment extends Fragment implements View.OnClickListener {

    View root;

    EditText edtBuilding;
    EditText edtPlate;
    EditText edtHostSuite;

    RadioGroup rdgHours;
    RadioButton rdoHoursSelected;

    Button btnCancel;
    Button btnParkCar;

    int buildingCode;

    String parkingDuration;
    String carPlateNumber;
    int hostSuiteNumber;
    String parkingDate;
    String parkingTime;
    double parkingCost;

    Parking parking;
    List<Parking> allParking;
    List<User> allUsers;

    private ParkingViewModel parkingViewModel;
    private UserViewModel userViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        parkingViewModel = new ViewModelProvider(this, new ParkingAppViewModelFactory(getActivity().getApplication())).get(ParkingViewModel.class);
        userViewModel = new ViewModelProvider(this, new ParkingAppViewModelFactory(getActivity().getApplication())).get(UserViewModel.class);
        root = inflater.inflate(R.layout.fragment_add_parking, container, false);
        final Toolbar toolbar = root.findViewById(R.id.toolbar);
        final TextView textView = root.findViewById(R.id.tv_add_parking_title);
        parkingViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                toolbar.setTitle(R.string.menu_parking);
                textView.setText(s);
            }
        });

        userViewModel.getAllUsers().observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                allUsers = users;
            }
        });

        parkingViewModel.getAllParking().observe(getViewLifecycleOwner(), new Observer<List<Parking>>() {
            @Override
            public void onChanged(List<Parking> parkings) {
                allParking = parkings;
            }
        });

        this.referWidgets();

        return root;
    }

    private void referWidgets(){
        edtBuilding = root.findViewById(R.id.edt_building);
        edtPlate = root.findViewById(R.id.edt_plate);
        edtHostSuite = root.findViewById(R.id.edt_host_suite);

        rdgHours = root.findViewById(R.id.rdg_hours);

        btnCancel = root.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(this);

        btnParkCar = root.findViewById(R.id.btn_park_car);
        btnParkCar.setOnClickListener(this);
    }

    private boolean validateUserData(){
        boolean allVerifications = true;

        if (edtBuilding.getText().toString().isEmpty())
        {
            edtBuilding.setError("Please enter the Building Code");
            allVerifications = false;
        }

        if(rdgHours.getCheckedRadioButtonId() == -1){
            rdoHoursSelected.setError("Please select a Parking Duration option");
        }

        if (edtPlate.getText().toString().isEmpty())
        {
            edtPlate.setError("Please enter your Car Plate Number");
            allVerifications = false;
        }

        if (edtHostSuite.getText().toString().isEmpty())
        {
            edtHostSuite.setError("Please enter the Suite Number of Host");
            allVerifications = false;
        }

        return allVerifications;

    }

    private void createParking(){
//        if(authenticateUserPlateNumber(edtPlate.getText().toString())){
            buildingCode = Integer.parseInt(edtBuilding.getText().toString());
            rdoHoursSelected = root.findViewById(rdgHours.getCheckedRadioButtonId());
            parkingDuration = rdoHoursSelected.getText().toString();
            carPlateNumber = edtPlate.getText().toString();
            hostSuiteNumber = Integer.parseInt(edtHostSuite.getText().toString());
            parking = new Parking(buildingCode, parkingDuration, carPlateNumber, hostSuiteNumber);
            parking.setParkingDate(parkingDate);
            parking.setParkingTime(parkingTime);
            parking.calculateParkingCost();
            parkingCost = parking.getParkingCost();
            Log.e("ParkingFragment", parking.toString());
            parkingViewModel.insert(parking);
//        }
//        else{
//            edtPlate.setError("The Plate Number enter does not match. Please try again");
//        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_cancel:
            case R.id.btn_park_car:
                if(validateUserData()){
                    this.chooseDate();
                    this.createParking();
                    this.openParkingReceiptFragment();
                }
        }
    }

    private boolean authenticateUserPlateNumber(String carPlateNumber){
        for(User user : allUsers) {
            if (user.getPlateNumber().equals(carPlateNumber)) {
                return true;
            }
        }
        return false;
    }

    private void chooseDate() {
        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, ''yy", Locale.CANADA);;
        parkingDate = sdf.format(calendar.getTime());

        sdf = new SimpleDateFormat("h:mm a", Locale.CANADA);
        parkingTime = sdf.format(calendar.getTime());
    }

    private void openParkingReceiptFragment(){
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        Bundle bundle = new Bundle();
        bundle.putInt("EXTRA_BUILDING_CODE", buildingCode);
        bundle.putString("EXTRA_PARKING_DURATION", parkingDuration);
        bundle.putString("EXTRA_CAR_PLATE_NUMBER", carPlateNumber);
        bundle.putInt("EXTRA_HOST_SUITE_NUMBER", hostSuiteNumber);
        bundle.putString("EXTRA_PARKING_DATE", parkingDate);
        bundle.putString("EXTRA_PARKING_TIME", parkingTime);
        bundle.putDouble("EXTRA_PARKING_COST", parkingCost);
        navController.navigate(R.id.action_nav_parking_to_nav_parking_receipt, bundle);

    }

}