package com.example.prog20082_final_android_parking_app_group_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.prog20082_final_android_parking_app_group_project.model.User;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener
{

    String name;
    String email;
    String password;
    String phoneNumber;
    String plateNumber;
    String cardNumber;
    String expiryDate;
    String cardName;
    String cardCVV;

    Button btnSignUp, btnCancel;

    EditText edtFullName;
    EditText edtEmail;
    EditText edtPassword;
    EditText edtPhone;
    EditText edtPlateNumber;
    EditText edtCardNumber;
    EditText edtCardName;
    EditText edtExpiryDate;
    EditText edtCVV;

    public static final String EXTRA_REPLY = "com.example.project.REPLY";



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnSignUp = findViewById(R.id.btn_signup);
        btnSignUp.setOnClickListener(this);

        btnCancel = findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(this);

        edtFullName = findViewById(R.id.edt_fullname);
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        edtPhone = findViewById(R.id.edt_phone);
        edtPlateNumber = findViewById(R.id.edt_platenumber);
        edtCardNumber = findViewById(R.id.edt_cardnumber);
        edtCardName = findViewById(R.id.edt_cardname);
        edtExpiryDate = findViewById(R.id.edt_expirydate);
        edtCVV = findViewById(R.id.edt_cvv);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btn_signup:
                if (this.verifyData())
                {
                    this.createUserAndReply();
                    //this.openSignInActivity();
                }
        }

    }

    private void createUserAndReply()
    {
        name = edtFullName.getText().toString();
        email = edtEmail.getText().toString();
        password = edtPassword.getText().toString();
        phoneNumber = edtPhone.getText().toString();
        plateNumber = edtPlateNumber.getText().toString();
        cardNumber = edtCardNumber.getText().toString();
        expiryDate = edtExpiryDate.getText().toString();
        cardName = edtCardName.getText().toString();
        cardCVV = edtCVV.getText().toString();


        User user = new User(name, email, password, phoneNumber, plateNumber, cardName, cardNumber, expiryDate, cardCVV);
        Log.e("SignUpActivity", user.toString());

        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, user);
        setResult(RESULT_OK, replyIntent);
        finish();
    }

    private boolean verifyData()
    {
        boolean allVerifications = true;

        if (edtFullName.getText().toString().isEmpty())
        {
            edtFullName.setError("Please enter your name!!");
            allVerifications = false;
        }

        if (edtEmail.getText().toString().isEmpty())
        {
            edtEmail.setError("Please enter in an email!!");
            allVerifications = false;
        }

        if (edtPassword.getText().toString().isEmpty())
        {
            edtPassword.setError("You have to enter in your password");
            allVerifications = false;
        }

        if (edtPhone.getText().toString().isEmpty())
        {
            edtPhone.setError("Please provide us with your phone number");
            allVerifications = false;
        }

        if (edtPlateNumber.getText().toString().isEmpty())
        {
            edtPlateNumber.setError("Please enter in your plate number");
            allVerifications = false;
        }

        if (edtCardNumber.getText().toString().isEmpty())
        {
            edtCardNumber.setError("Please enter in your card number");
            allVerifications = false;
        }


        if (edtCardName.getText().toString().isEmpty())
        {
            edtCardName.setError("Please enter in your card name");
            allVerifications = false;
        }

        if (edtExpiryDate.getText().toString().isEmpty())
        {
            edtExpiryDate.setError("Please enter in your card's expiry date");
            allVerifications = false;
        }

        if (edtCVV.getText().toString().isEmpty())
        {
            edtCVV.setError("Please enter in your CVV number");
            allVerifications = false;
        }

        return allVerifications;

    }
}
