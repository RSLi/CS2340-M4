package com.example.m4.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.m4.R;
import com.example.m4.models.Models;

public class MainAppActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);
        imageView = (ImageView)findViewById(R.id.imageView3);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        imageView.setAnimation(animation);

        Button mBtnUserProfile = (Button) findViewById(R.id.btn_user_profile);
        Button mBtnLogout = (Button) findViewById(R.id.button_logout);
        Button mBtnCreateReport = (Button) findViewById(R.id.btn_create_report);
        Button mBtnViewReport = (Button) findViewById(R.id.btn_view_report);

        //set up buttons
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

        mBtnCreateReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainAppActivity.this, CreateReportActivity.class);
                startActivity(intent);
            }
        });

        mBtnViewReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainAppActivity.this, ViewReportActivity.class);
                startActivity(intent);
            }
        });
    }
}
