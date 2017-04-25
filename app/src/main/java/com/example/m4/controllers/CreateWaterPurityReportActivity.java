package com.example.m4.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.m4.R;
import com.example.m4.models.DataBaseRequests;
import com.example.m4.models.Models;
import com.example.m4.models.WaterOverallCondition;
import com.example.m4.models.WaterPurityReport;

import java.util.Date;
import java.util.Locale;

public class CreateWaterPurityReportActivity extends AppCompatActivity {
    private final WaterPurityReport report = new WaterPurityReport();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_water_purity_report);
        //get buttons
        Button btnPurityReportSubmit = (Button)findViewById(R.id.btn_purity_submit);
        Button btnPurityReportCancel = (Button)findViewById(R.id.btn_purity_cancel);
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        TextView text1 = (TextView) findViewById(R.id.textView4);
        text1.startAnimation(animation);
        TextView text2 = (TextView) findViewById(R.id.textView6);
        text2.startAnimation(animation);

        //set up spinner to show the water over conditions
        final Spinner spWaterOverallCondition = (Spinner)findViewById(R.id.spinner_water_overall_condition);
        spWaterOverallCondition.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, WaterOverallCondition.values()));

        //display reporter name, report number, and dateTime on screen by auto-generation
        final TextView reporter = (TextView) findViewById((R.id.field_purity_reporter));
        TextView reportNo = (TextView) findViewById((R.id.field_purity_num));
        TextView dateTime = (TextView) findViewById((R.id.field_date_time));
        reporter.setText(Models.accountInSession.getUsername());
        reportNo.setText((String.format(Locale.US, "%d", Models.getReportsAsList().size())));
        Date date = new Date();
        dateTime.setText(date.toString());

        //get the info user typed in
        final EditText longitude = (EditText) findViewById(R.id.edit_longitude);
        final EditText latitude = (EditText) findViewById(R.id.edit_latitude);
        final EditText contaminantPPM = (EditText) findViewById(R.id.edit_contaminant_PPM);
        final EditText virusPPM = (EditText) findViewById(R.id.edit_Virus_PPm);
//        //for testing other month, uncomment this line
//        final EditText month = (EditText) findViewById(R.id.edit_month);

        //new report should be created once click submit button
        btnPurityReportSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                report.setWaterOverallCondition((WaterOverallCondition)spWaterOverallCondition.getSelectedItem());
                report.setLongitude(Double.parseDouble(longitude.getText().toString()));
                report.setLatitude(Double.parseDouble(latitude.getText().toString()));
                report.setContaminantPPM(Double.parseDouble(contaminantPPM.getText().toString()));
                report.setVirusPPM(Double.parseDouble(virusPPM.getText().toString()));
//                //for testing other month, uncomment this line
//                report.setMonth(Integer.parseInt(month.getText().toString()));
                Models.submitReport(report);
                createPurityDB();
                Intent intent = new Intent(CreateWaterPurityReportActivity.this, CreateReportActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        //set up cancel button
        btnPurityReportCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateWaterPurityReportActivity.this, CreateReportActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void createPurityDB() {
        DataBaseRequests.createPurityReport(this, report);
    }
}
