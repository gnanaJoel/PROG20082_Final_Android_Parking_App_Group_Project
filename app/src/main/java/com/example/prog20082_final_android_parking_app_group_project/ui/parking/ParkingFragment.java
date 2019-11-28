package com.example.prog20082_final_android_parking_app_group_project.ui.parking;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.prog20082_final_android_parking_app_group_project.ParkingAppViewModelFactory;
import com.example.prog20082_final_android_parking_app_group_project.R;
import com.example.prog20082_final_android_parking_app_group_project.ui.parking_receipt.ParkingReceiptFragment;


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
        parkingViewModel = new ViewModelProvider(this, new ParkingAppViewModelFactory(getActivity().getApplication())).get(ParkingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add_parking, container, false);
        final Toolbar toolbar = root.findViewById(R.id.toolbar);
        final TextView textView = root.findViewById(R.id.tv_add_parking_title);
        parkingViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                toolbar.setTitle(R.string.menu_parking);
                textView.setText(s);
            }
        });

        this.referWidgets(root);

        return root;
    }

    void referWidgets(View root){
        edtBuilding = root.findViewById(R.id.edt_building);
        edtPlate = root.findViewById(R.id.edt_plate);
        edtHostSuit = root.findViewById(R.id.edt_host_suite);

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
                this.openParkingReceiptFragment();
        }
    }

    void openParkingReceiptFragment(){
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.action_nav_parking_to_nav_parking_receipt);

    }

}