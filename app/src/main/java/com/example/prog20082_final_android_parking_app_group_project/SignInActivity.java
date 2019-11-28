package com.example.prog20082_final_android_parking_app_group_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prog20082_final_android_parking_app_group_project.model.User;
import com.example.prog20082_final_android_parking_app_group_project.viewmodel.UserViewModel;

import java.util.List;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener
{
    EditText userEmail, userPassword;
    Button signIn;
    TextView signUp, forgetPassword;
    Switch remember;

    String emailAddress = "";
    String password = "";


    public static final int SIGN_UP_REQUEST_CODE = 1;
    public static final String USER_EMAIL = "EMAIL";
    public static final String USER_PASSWORD = "PASSWORD";
    public static final String USER_PREFERENCE = "com.example.project.userpref";

    UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        this.referWidgets();

        userViewModel = new UserViewModel(getApplication());

        userViewModel.getAllUsers().observe(SignInActivity.this, new Observer<List<User>>()
        {
            @Override
            public void onChanged(List<User> users)
            {
                for (User user: users)
                {
                    Log.e("SignInActivity", user.toString());
                }
            }
        });

        this.getRememberedData();
    }

    void referWidgets()
    {
        userEmail = findViewById(R.id.edt_email);
        userPassword = findViewById(R.id.edt_password);

        signIn = findViewById(R.id.btn_signIn);
        signIn.setOnClickListener(this);

        signUp = findViewById(R.id.txt_signUp);
        signUp.setOnClickListener(this);

        forgetPassword = findViewById(R.id.txt_forgetPassword);
        forgetPassword.setOnClickListener(this);

        remember = findViewById(R.id.swt_remember);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btn_signIn:
                this.signIn();
                break;



            case R.id.txt_signUp:
                this.signUp();
                break;

            case R.id.txt_forgetPassword:
                this.resetPassword();
                break;

        }
    }

    void signIn()
    {
        emailAddress = userEmail.getText().toString();
        password = userPassword.getText().toString();

        if(this.authenticateParking(emailAddress, password))
        {
            if (remember.isChecked())
            {
                this.rememberData();
            }

            else
            {
                this.forgetData();
            }

            Toast.makeText(this, "Login was successful",Toast.LENGTH_LONG).show();

            this.openMainActivity();


        }


        else
        {
            Toast.makeText(this, "The email and password was incorrect, please try again!", Toast.LENGTH_LONG).show();
        }

    }

    void signUp()
    {
        Intent intentSignUp = new Intent(SignInActivity.this, SignUpActivity.class);
        intentSignUp.putExtra("EXTRA_EMAIL", emailAddress);
        startActivityForResult(intentSignUp, SIGN_UP_REQUEST_CODE);
    }

    void resetPassword()
    {

        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.reset_password, null);

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Reset Password")
                .setMessage("Reset your password here")
                .setView(dialogView)
                .setPositiveButton("Reset", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText edt_email = dialogView.findViewById(R.id.edt_email);
                        EditText edt_password = dialogView.findViewById(R.id.edt_password);

                        String edtEmail = edt_email.getText().toString();
                        String edtPassword = edt_password.getText().toString();

                        List<User> allUsers = userViewModel.getAllUsers().getValue();

                        for(User user: allUsers){
                            if (user.getEmail().equals(edtEmail)){

                                user.setPassword(edtPassword);
                                userViewModel.updateUser(user);
                            }

                            Log.e("PasswordUpdate", user.toString());
                        }
                    }
                }).setNegativeButton("Cancel", null)
                .create();
        alertDialog.show();

    }

    void openMainActivity()
    {
        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
        intent.putExtra("EMAIL_EXTRA", emailAddress);
        SignInActivity.this.startActivity(intent);
        //intent.putExtra("extra_email", authenticateParking(emailAddress))
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGN_UP_REQUEST_CODE)
        {
            if(resultCode == RESULT_OK)
            {
                User newUser = (User) data.getSerializableExtra("com.example.project.REPLY");
                Log.e("SIGN_IN_ACTIVITY", newUser.toString());

                userViewModel.insert(newUser);
            }
        }
    }

    private boolean authenticateParking(String emailAddress, String password)
    {
        List<User> allUsers = userViewModel.getAllUsers().getValue();

        for(User user: allUsers)
        {
            if (user.getEmail().equals(emailAddress) && user.getPassword().equals(password))
            {
                return true;
            }
        }
        return false;
    }

    private void rememberData()
    {
        SharedPreferences sharedPref = getSharedPreferences(USER_PREFERENCE, Context.MODE_PRIVATE);

        sharedPref.edit().putString(USER_EMAIL, userEmail.getText().toString()).commit();
        sharedPref.edit().putString(USER_PASSWORD, userPassword.getText().toString()).commit();
    }


    private void forgetData()
    {

        SharedPreferences sharedPref = getSharedPreferences(USER_PREFERENCE, Context.MODE_PRIVATE);

        sharedPref.edit().clear().apply();

    }

    private void getRememberedData()
    {
        SharedPreferences sharedPref = getSharedPreferences(USER_PREFERENCE, Context.MODE_PRIVATE);

        userEmail.setText(sharedPref.getString(USER_EMAIL, ""));
        userPassword.setText(sharedPref.getString(USER_PASSWORD, ""));

    }



}
