package com.example.m4.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.m4.R;
import com.example.m4.models.Models;
import com.example.m4.models.Permission;

public class CreateReportActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_report);
        //get buttons
        Button btnCreateWaterSourceReport = (Button) findViewById(R.id.btn_create_water_source_report);
        Button btnCreateWaterPurityReport = (Button) findViewById(R.id.btn_create_water_purity_report);
        //set buttons
        btnCreateWaterPurityReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Models.getAccountInSession().hasPermission(Permission.SUBMIT_REPORT)) {
                    Intent intent = new Intent(CreateReportActivity.this, CreateWaterPurityReportActivity.class);
                    startActivity(intent);
                } else {
                    new AlertDialog.Builder(CreateReportActivity.this)
                            .setTitle("No Permission")
                            .setMessage("Only Workers and Managers can submit reports").show();
                }
            }
        });

        btnCreateWaterSourceReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateReportActivity.this, CreateWaterSourceReportActivity.class);
                startActivity(intent);
            }
        });


    }
}
