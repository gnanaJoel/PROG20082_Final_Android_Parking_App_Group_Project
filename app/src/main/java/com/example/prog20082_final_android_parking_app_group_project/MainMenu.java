package com.example.prog20082_final_android_parking_app_group_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.prog20082_final_android_parking_app_group_project.model.User;
import com.example.prog20082_final_android_parking_app_group_project.viewmodel.UserViewModel;

import java.util.List;

public class MainMenu extends AppCompatActivity implements View.OnClickListener {

    Button btnUpdateInfo;
    UserViewModel userViewModel;
    User userInfo;
    String EmailAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        btnUpdateInfo = findViewById(R.id.btnUpdateInfo);
        btnUpdateInfo.setOnClickListener(this);

        userViewModel = new UserViewModel(getApplication());

        EmailAddress = getIntent().getStringExtra("EMAIL_EXTRA");

        userViewModel.getAllUsers().observe(MainMenu.this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                //task when the data changes
                for (User user : users) {
                    if (user.getEmail().equals(EmailAddress)) {
                        userInfo = user;

                    }

                    Log.e("SignInActivity", user.toString());
                }
            }
        });

    }

    void updateInfo(){
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.display_info, null);

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Reset Password")
                .setMessage("Please reset the password")
                .setView(dialogView)
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        EditText edt_name = dialogView.findViewById(R.id.edtName);
                        EditText edt_password = dialogView.findViewById(R.id.edtPassword);
                        EditText edt_phoneNumber = dialogView.findViewById(R.id.edtPhoneNumber);
                        EditText edt_licensePlate = dialogView.findViewById(R.id.edtCarPlateNumber);
                        EditText edt_cardNumber = dialogView.findViewById(R.id.edtCarNumber);
                        EditText edt_expirationDate = dialogView.findViewById(R.id.edtExpirydate);
                        EditText edt_cardName = dialogView.findViewById(R.id.edtCardName);
                        EditText edt_cvvNumber = dialogView.findViewById(R.id.edtCvvNumber);


                        String edtName = edt_name.getText().toString();
                        String edtPassword = edt_password.getText().toString();
                        String edtPhoneNumber = edt_phoneNumber.getText().toString();
                        String edtLicensePlate = edt_licensePlate.getText().toString();
                        String edtCardNumber = edt_cardNumber.getText().toString();
                        String edtExpirationDate = edt_expirationDate.getText().toString();
                        String edtCardName = edt_cardName.getText().toString();
                        String edtCvvNumber = edt_cvvNumber.getText().toString();

                        userInfo.setFullName(edtName);
                        userInfo.setPassword(edtPassword);
                        userInfo.setPhoneNumber(edtPhoneNumber);
                        userInfo.setPlateNumber(edtLicensePlate);
                        userInfo.setCardNumber(edtCardNumber);
                        userInfo.setExpiringDate(edtExpirationDate);
                        userInfo.setCardName(edtCardName);
                        userInfo.setCvvNumber(edtCvvNumber);
                        userViewModel.updateUser(userInfo);


                    }
                })
                .setNegativeButton("Cancel", null)
                .create();

        alertDialog.show();
    }




    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnUpdateInfo:
                this.updateInfo();
                break;
        }
    }
}
