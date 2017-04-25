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
import com.example.m4.models.WaterCondition;
import com.example.m4.models.WaterSourceReport;
import com.example.m4.models.WaterType;

import java.util.Date;
import java.util.Locale;

public class CreateWaterSourceReportActivity extends AppCompatActivity {
    private final WaterSourceReport report = new WaterSourceReport();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_water_source_report);
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        TextView text1 = (TextView) findViewById(R.id.textView26);
        text1.startAnimation(animation);
        TextView text2 = (TextView) findViewById(R.id.textView28);
        text2.startAnimation(animation);

        //get buttons
        Button btnSourceReportSubmit = (Button) findViewById(R.id.btn_source_submit);
        Button btnSourceReportCancel = (Button) findViewById(R.id.btn_source_cancel);

        //set up waterType and waterCondition spinners
        final Spinner spWaterType = (Spinner) findViewById(R.id.spinner_water_type);
        final Spinner spWaterCondition = (Spinner) findViewById(R.id.spinner_condition);
        spWaterType.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, WaterType.values()));
        spWaterCondition.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, WaterCondition.values()));
        //get info user typed in
        final EditText longitude = (EditText) findViewById(R.id.edit_longitude);
        final EditText latitude = (EditText) findViewById(R.id.edit_latitude);

        //display reporter name, report number, and dateTime on screen by auto-generation
        TextView reporter = (TextView) findViewById((R.id.field_source_reporter));
        TextView reportNo = (TextView) findViewById((R.id.field_source_num));
        TextView dateTime = (TextView) findViewById((R.id.field_date_time));
        reporter.setText(Models.accountInSession.getUsername());
        reportNo.setText((String.format(Locale.US, "%d", Models.getReportsAsList().size())));
        Date date = new Date();
        dateTime.setText(date.toString());

        //new report should be created once click submit button
        btnSourceReportSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                report.setWaterCondition((WaterCondition)spWaterCondition.getSelectedItem());
                report.setWaterType((WaterType)spWaterType.getSelectedItem());
                report.setLongitude(Double.parseDouble(longitude.getText().toString()));
                report.setLatitude(Double.parseDouble(latitude.getText().toString()));
                Models.submitReport(report);
                addSourceDB();

                Intent intent = new Intent(CreateWaterSourceReportActivity.this, CreateReportActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        //set up cancel button
        btnSourceReportCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateWaterSourceReportActivity.this, CreateReportActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }
    private void addSourceDB() {
        DataBaseRequests.createSourceReport(this, report);
    }
}
