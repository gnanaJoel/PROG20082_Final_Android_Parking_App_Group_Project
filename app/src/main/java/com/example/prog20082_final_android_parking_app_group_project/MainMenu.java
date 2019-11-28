package com.example.prog20082_final_android_parking_app_group_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toolbar;

import com.example.prog20082_final_android_parking_app_group_project.model.User;
import com.example.prog20082_final_android_parking_app_group_project.viewmodel.UserViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainMenu extends AppCompatActivity {

    Button btnUpdateInfo;
    UserViewModel userViewModel;
    User userInfo;
    String EmailAddress;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      btnUpdateInfo = findViewById(R.id.btnUpdateInfo);
//      btnUpdateInfo.setOnClickListener(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
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
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
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

    //hamburger
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Log.e("MIRZA", "ONITEMSLECTED");
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
        //getMenuInflater().inflate(R.menu.main, menu);
        //return super.onCreateOptionsMenu(menu);
        //return false;
    }


    public boolean onContextItemSelected(MenuItem item) {
        onOptionsItemSelected(item);
        return true;
    }

    // hamburger
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.e("MIRZA", "ONITEMSLECTED");
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




//    @Override
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.btnUpdateInfo:
//                this.updateInfo();
//                break;
//        }
//    }
}
