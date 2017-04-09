package com.example.m4.Controllers;

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

public class ViewOneReport extends AppCompatActivity {
    TextView mReportNumber;
    TextView mReporter;
    TextView mDate;
    TextView mLocation;
    TextView mTV1;
    TextView mTV2;
    TextView mTV3;
    TextView mTV4;
    TextView mTV5;
    TextView mTV6;
    ArrayList<Report> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_one_report);
        Intent mIntent = getIntent();
        //get the report number of the clicked report
        int intValue = mIntent.getIntExtra("reportNo", 0);
        //get report from reportList
        list = Models.getReportsAsList();

        //get buttons and textviews
        mReportNumber = (TextView) findViewById(R.id.reportNum);
        mReporter = (TextView) findViewById(R.id.reporter);
        mDate = (TextView) findViewById(R.id.date);
        mLocation = (TextView) findViewById((R.id.location));
        mTV1 = (TextView) findViewById(R.id.textView100);
        mTV2 = (TextView) findViewById(R.id.textView101);
        mTV3 = (TextView) findViewById(R.id.textView102);
        mTV4 = (TextView) findViewById((R.id.textView103));
        mTV5 = (TextView) findViewById((R.id.textView104));
        mTV6 = (TextView) findViewById((R.id.textView105));

        //set textView to detailed report info
        mReportNumber.setText(""+ list.get(intValue).getReportNumber());
        mReporter.setText("" + list.get(intValue).getReporterUsername());
        mDate.setText("" + list.get(intValue).getDate());
        mLocation.setText("" + list.get(intValue).getLongitude() + ", " + list.get(intValue).getLatitude());

        //display relevant info according to the report type
        if (list.get(intValue) instanceof WaterSourceReport){
            mTV1.setText("Water Condition:");
            mTV2.setText("" + ((WaterSourceReport)list.get(intValue)).getWaterCondition());
            mTV3.setText("Water Type:");
            mTV4.setText("" + ((WaterSourceReport)list.get(intValue)).getWaterType());
            mTV5.setText("");
            mTV6.setText("");
        } else if (list.get(intValue) instanceof WaterPurityReport){
            mTV1.setText("Water Condition:");
            mTV2.setText("" + (((WaterPurityReport) list.get(intValue)).getWaterOverallCondition()));
            mTV3.setText("Virus PPM:");
            mTV4.setText("" + (((WaterPurityReport) list.get(intValue)).getVirusPPM()));
            mTV5.setText("Contaminant PPM:");
            mTV6.setText("" + (((WaterPurityReport) list.get(intValue)).getContaminantPPM()));
        }
    }
}
