package com.example.m4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.m4.models.WaterCondition;
import com.example.m4.models.WaterOverallCondition;
import com.example.m4.models.WaterPurityReport;
import com.example.m4.models.WaterSourceReport;
import com.example.m4.models.WaterType;

/**
 * Created by yuchen on 2017/3/5.
 */

public class CreateWaterPurityReportActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_water_purity_report);

        Button btnPurityReportSubmit = (Button)findViewById(R.id.btn_purity_submit);
        Button btnPurityReportCancel = (Button)findViewById(R.id.btn_purity_cancel);
        final Spinner spWaterOverallCondition = (Spinner)findViewById(R.id.spinner_water_overall_condition);
        spWaterOverallCondition.setAdapter(new ArrayAdapter<WaterOverallCondition>(this, android.R.layout.simple_spinner_item, WaterOverallCondition.values()));

        btnPurityReportSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WaterPurityReport report = new WaterPurityReport();
                report.setWaterOverallCondition((WaterOverallCondition)spWaterOverallCondition.getSelectedItem());
                Intent intent = new Intent(CreateWaterPurityReportActivity.this, CreateReportActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        btnPurityReportCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateWaterPurityReportActivity.this, CreateReportActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}
