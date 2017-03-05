package com.example.m4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by yuchen on 2017/3/5.
 */

public class CreateReportActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_report);

        Button btnCreateWaterSourceReport = (Button) findViewById(R.id.btn_create_water_source_report);
        Button btnCreateWaterPurityReport = (Button) findViewById(R.id.btn_create_water_purity_report);

        btnCreateWaterPurityReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateReportActivity.this, CreateWaterPurityReport.class);
                startActivity(intent);
            }
        });
    }
}
