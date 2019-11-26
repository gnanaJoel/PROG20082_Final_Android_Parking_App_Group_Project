package com.example.prog20082_final_android_parking_app_group_project.ui.call;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.prog20082_final_android_parking_app_group_project.R;

public class CallFragment extends Fragment implements View.OnClickListener {

    Button btnCall;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_call, container, false);

        btnCall = root.findViewById(R.id.btn_call);
        btnCall.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_call:
                this.makeCall();
                break;
        }
    }

    private void makeCall() {

        Intent callIntent = new Intent(Intent.ACTION_CALL);

        callIntent.setData(Uri.parse("tel:1234567890"));

        if(ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(),
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            Log.e("CallFragment", "Call permission not granted");
            return;
        }
        startActivity(callIntent);

    }
}