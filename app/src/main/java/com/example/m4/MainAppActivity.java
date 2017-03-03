package com.example.m4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.m4.models.Models;

public class MainAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);

        Button mBtnUserProfile = (Button) findViewById(R.id.btn_user_profile);
        Button mBtnLogout = (Button) findViewById(R.id.button_logout);


        mBtnUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainAppActivity.this, ViewProfileActivity.class);
                startActivity(intent);
            }
        });

        mBtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Models.logout();
                Intent intent = new Intent(MainAppActivity.this, WelcomeActivity.class);
                startActivity(intent);
            }
        });

    }
}
