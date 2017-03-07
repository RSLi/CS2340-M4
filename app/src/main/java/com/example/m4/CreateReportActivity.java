package com.example.m4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class CreateReportActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_report);
        //get buttons
        Button btnCreateWaterSourceReport = (Button) findViewById(R.id.btn_create_water_source_report);
        Button btnCreateWaterPurityReport = (Button) findViewById(R.id.btn_create_water_purity_report);
        Button btnCancel = (Button) findViewById(R.id.button_cancel);
        //set buttons
        btnCreateWaterPurityReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateReportActivity.this, CreateWaterPurityReportActivity.class);
                startActivity(intent);
            }
        });

        btnCreateWaterSourceReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateReportActivity.this, CreateWaterSourceReportActivity.class);
                startActivity(intent);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateReportActivity.this, MainAppActivity.class);
                startActivity(intent);
            }
        });

    }
}
