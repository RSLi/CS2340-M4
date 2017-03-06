package com.example.m4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.m4.models.Models;
import com.example.m4.models.WaterCondition;
import com.example.m4.models.WaterOverallCondition;
import com.example.m4.models.WaterPurityReport;
import com.example.m4.models.WaterSourceReport;
import com.example.m4.models.WaterType;

import java.util.Date;

/**
 * Created by yuchen on 2017/3/5.
 */

public class CreateWaterPurityReportActivity extends AppCompatActivity {
    final WaterPurityReport report = new WaterPurityReport();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_water_purity_report);

        Button btnPurityReportSubmit = (Button)findViewById(R.id.btn_purity_submit);
        Button btnPurityReportCancel = (Button)findViewById(R.id.btn_purity_cancel);
        final Spinner spWaterOverallCondition = (Spinner)findViewById(R.id.spinner_water_overall_condition);
        spWaterOverallCondition.setAdapter(new ArrayAdapter<WaterOverallCondition>(this, android.R.layout.simple_spinner_item, WaterOverallCondition.values()));
        TextView reporter = (TextView) findViewById((R.id.field_purity_reporter));
        TextView reportNo = (TextView) findViewById((R.id.field_purity_num));
        TextView dateTime = (TextView) findViewById((R.id.field_date_time));
        reporter.setText(Models.accountInSession.getUsername());
        reportNo.setText(((Integer) Models.getReportsAsList().size()).toString());
        Date date = new Date();
        dateTime.setText(date.toString());
        final EditText location = (EditText) findViewById(R.id.edit_location);
        final EditText contaminantPPM = (EditText) findViewById(R.id.edit_contaminant_PPM);
        final EditText virusPPM = (EditText) findViewById(R.id.edit_Virus_PPm);

        btnPurityReportSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                report.setWaterOverallCondition((WaterOverallCondition)spWaterOverallCondition.getSelectedItem());
                report.setLocation(location.toString());
                report.setContaminantPPM(Double.parseDouble(contaminantPPM.getText().toString()));
                report.setVirusPPM(Double.parseDouble(virusPPM.getText().toString()));
                Models.submitReport(report);
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
