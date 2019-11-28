package com.example.prog20082_final_android_parking_app_group_project;

import android.Manifest;
import android.content.DialogInterface;
import android.os.Bundle;

import com.example.prog20082_final_android_parking_app_group_project.model.User;
import com.example.prog20082_final_android_parking_app_group_project.viewmodel.UserViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    Button btnUpdateInfo;
    UserViewModel userViewModel;
    User userInfo;
    String EmailAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_call, R.id.nav_email)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.CALL_PHONE}, 1);
        userViewModel = new UserViewModel(getApplication());
        EmailAddress = getIntent().getStringExtra("EMAIL_EXTRA");

        userViewModel.getAllUsers().observe(MainActivity.this, new Observer<List<User>>() {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onContextItemSelected(MenuItem item) {
        onOptionsItemSelected(item);
        return true;
    }

    // hamburger
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){

            case R.id.action_update:
                this.updateInfo();
                return  true;
            //break;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void updateInfo(){
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.display_info, null);

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Update Information")
                .setMessage("Update your Information")
                .setView(dialogView)
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        EditText edt_name = dialogView.findViewById(R.id.edtName);
                        EditText edt_password = dialogView.findViewById(R.id.edtPassword);
                        EditText edt_phoneNumber = dialogView.findViewById(R.id.edtPhoneNumber);
                        EditText edt_licensePlate = dialogView.findViewById(R.id.edtCarPlateNumber);
                        EditText edt_cardNumber = dialogView.findViewById(R.id.edtCarNumber);
                        EditText edt_expirationDate = dialogView.findViewById(R.id.edtExpiryDate);
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
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}
