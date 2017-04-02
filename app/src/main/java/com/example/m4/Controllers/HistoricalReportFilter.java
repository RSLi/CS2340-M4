package com.example.m4.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.m4.R;
import com.example.m4.models.Models;
import com.example.m4.models.Report;
import com.example.m4.models.WaterPurityReport;

import java.util.ArrayList;

/**
 * Created by yuchen on 2017/4/1.
 */

public class HistoricalReportFilter extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historical_report_filter);
        ArrayList<Report> allReportsList = Models.getReportsAsList();
        final ArrayList<WaterPurityReport> purityReportList = new ArrayList<>();
        final ArrayList<String> locations = new ArrayList<>();
        ArrayList<Integer> years = new ArrayList<>();
        for (Report r: allReportsList) {
            if (r instanceof WaterPurityReport) {
                purityReportList.add((WaterPurityReport) r);
                String location = "" + r.getLongitude() + ", " + r.getLatitude();
                if (!locations.contains(location)) {
                    locations.add(location);
                }
                if (!years.contains(r.getYear())) {
                    years.add(r.getYear());
                }
            }
        }
        final Spinner locationSpinner = (Spinner) findViewById(R.id.spinner_Location);
        final Spinner yearSpinner = (Spinner) findViewById(R.id.spinner_year);
        ArrayAdapter<String> locationAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, locations);
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(locationAdapter);
        ArrayAdapter<Integer> yearAdapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, years);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapter);

        Button submit = (Button) findViewById(R.id.btn_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HistoricalReportFilter.this, ViewHistoricalReportActivity.class);
                intent.putExtra("Location", locationSpinner.getSelectedItem().toString());
                intent.putExtra("Year", Integer.parseInt(yearSpinner.getSelectedItem().toString()));
                startActivity(intent);
            }
        });
    }
}
