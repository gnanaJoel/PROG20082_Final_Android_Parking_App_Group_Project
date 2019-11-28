package com.example.prog20082_final_android_parking_app_group_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import static java.lang.Thread.sleep;

public class SplashActivity extends AppCompatActivity {

    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        thread = new Thread(){
            public void run(){

                try{

                    synchronized (this){

                        wait(3000);
                    }
                }
                catch (InterruptedException e){

                }

                finally {
                    Intent spIntent = new Intent(SplashActivity.this, SignInActivity.class);
                    startActivity(spIntent);
                    finish();
                }
            }
        };

        thread.start();
        }


        }
