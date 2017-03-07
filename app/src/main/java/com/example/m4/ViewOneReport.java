package com.example.m4;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.m4.models.Models;
import com.example.m4.models.Report;

import java.util.ArrayList;

public class ViewOneReport extends AppCompatActivity {
    TextView mReportNumber;
    TextView mReporter;
    TextView mDate;
    TextView mLocation;
    ArrayList<Report> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_one_report);
        Intent mIntent = getIntent();
        int intValue = mIntent.getIntExtra("rowNum", 0);
        list = Models.getReportsAsList();
        mReportNumber = (TextView) findViewById(R.id.reportNum);
        mReporter = (TextView) findViewById(R.id.reporter);
        mDate = (TextView) findViewById(R.id.date);
        mLocation = (TextView) findViewById((R.id.location));


        mReportNumber.setText(list.get(intValue).getReportNumber());
        mReporter.setText(list.get(intValue).getReporterUsername());
        mDate.setText(list.get(intValue).getDate().toString());
        mLocation.setText(list.get(intValue).getLocation());

    }

}
