package com.example.m4.controllers;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.widget.TextView;

import com.example.m4.R;
import com.example.m4.models.Models;
import com.example.m4.models.Report;
import com.example.m4.models.WaterPurityReport;
import com.example.m4.models.WaterSourceReport;

import java.util.ArrayList;
import java.util.Locale;

public class ViewOneReport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_one_report);
        Intent mIntent = getIntent();
        //get the report number of the clicked report
        int intValue = mIntent.getIntExtra("reportNo", 0);
        //get report from reportList
        //noinspection unchecked
        ArrayList<Report> list = Models.getReportsAsList();

        //get buttons and TextViews
        TextView mReportNumber = (TextView) findViewById(R.id.reportNum);
        TextView mReporter = (TextView) findViewById(R.id.reporter);
        TextView mDate = (TextView) findViewById(R.id.date);
        TextView mLocation = (TextView) findViewById((R.id.location));
        TextView mTV1 = (TextView) findViewById(R.id.textView100);
        TextView mTV2 = (TextView) findViewById(R.id.textView101);
        TextView mTV3 = (TextView) findViewById(R.id.textView102);
        TextView mTV4 = (TextView) findViewById((R.id.textView103));
        TextView mTV5 = (TextView) findViewById((R.id.textView104));
        TextView mTV6 = (TextView) findViewById((R.id.textView105));

        //set textView to detailed report info
        mReportNumber.setText(String.format(Locale.US, "%d", list.get(intValue).getReportNumber()));
        mReporter.setText(list.get(intValue).getReporterUsername());
        mDate.setText(list.get(intValue).getDate().toString());
        mLocation.setText("" + list.get(intValue).getLongitude() + ", " + list.get(intValue).getLatitude());

        //display relevant info according to the report type
        if (list.get(intValue) instanceof WaterSourceReport){
            mTV1.setText(getString(R.string.create_water_purity_report_condition));
            mTV2.setText(((WaterSourceReport) list.get(intValue)).getWaterCondition().toString());
            mTV3.setText(getString(R.string.create_water_source_report_water_type));
            mTV4.setText(((WaterSourceReport) list.get(intValue)).getWaterType().toString());
            mTV5.setText("");
            mTV6.setText("");
        } else if (list.get(intValue) instanceof WaterPurityReport){
            mTV1.setText(getString(R.string.create_water_purity_report_condition));
            mTV2.setText((((WaterPurityReport) list.get(intValue)).getWaterOverallCondition()).toString());
            mTV3.setText(getString(R.string.create_water_purity_report_virus_ppm));
            mTV4.setText(String.format(Locale.US, "%f",(((WaterPurityReport) list.get(intValue)).getVirusPPM())));
            mTV5.setText(getString(R.string.create_water_purity_report_contaminant_ppm));
            mTV6.setText(String.format(Locale.US, "%f", ((WaterPurityReport) list.get(intValue)).getContaminantPPM()));
        }
    }
}
