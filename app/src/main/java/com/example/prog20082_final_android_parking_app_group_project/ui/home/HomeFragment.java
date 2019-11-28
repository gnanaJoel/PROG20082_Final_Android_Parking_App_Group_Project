package com.example.prog20082_final_android_parking_app_group_project.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.prog20082_final_android_parking_app_group_project.R;
import com.example.prog20082_final_android_parking_app_group_project.SignInActivity;

import java.text.DateFormat;
import java.util.Date;

public class HomeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView txtTime= root.findViewById(R.id.text_home);

        String currentDateAndTime = DateFormat.getDateTimeInstance().format(new Date());
        txtTime.setText(currentDateAndTime);

        return root;
    }
}