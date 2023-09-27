package com.bit.bharatplus.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.bit.bharatplus.R;
import com.bit.bharatplus.utils.AndroidUtils;
import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {
    SharedPreferences sp;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_splash);
        sp = getSharedPreferences("data", 0);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if(mAuth.getCurrentUser() == null){
                intent = new Intent(getApplicationContext(), LoginActivity.class);
                }else{
                    if(!sp.getBoolean("profileCompleted", false)){
                        intent = new Intent(getApplicationContext(), CompleteProfileActivity.class);
                    }else
                        intent = new Intent(getApplicationContext(), MainActivity.class);
                }
                startActivity(intent);
                finishAffinity();
            }
        },3000);
    }
}