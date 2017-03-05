package com.example.m4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by yuchen on 2017/3/5.
 */

public class CreateWaterPurityReport extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_water_purity_report);

        Button btnPurityReportSubmit = (Button)findViewById(R.id.btn_purity_submit);
        Button btnPurityReportCancel = (Button)findViewById(R.id.btn_purity_cancel);

        btnPurityReportSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateWaterPurityReport.this, CreateReportActivity.class);
                startActivity(intent);
            }
        });

        btnPurityReportCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateWaterPurityReport.this, CreateReportActivity.class);
                startActivity(intent);
            }
        });
    }
}
