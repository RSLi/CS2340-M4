package com.example.m4.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.m4.R;
import com.example.m4.models.Models;
import com.example.m4.models.Report;
import com.example.m4.models.WaterPurityReport;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ViewPurityReportListActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_report_list);
        //add all the purity reports into a list
        // if in API 24+:
        // final ArrayList<Report> profileData = Models.getReportsAsList().stream().filter(report -> report instanceof WaterPurityReport).collect(Collectors.toList());
        ArrayList<Report> allReportList = Models.getReportsAsList();
        final ArrayList<WaterPurityReport> profileData = new ArrayList<WaterPurityReport>();
        for (Report report : allReportList) {
            if (report instanceof WaterPurityReport) {
                profileData.add((WaterPurityReport) report);
            }
        }


        final ListView listview = (ListView) findViewById(R.id.report_list);
        final ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, profileData);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                //view one report
                Intent intent = new Intent(ViewPurityReportListActivity.this, ViewOneReport.class);
                intent.putExtra("rowNum", arg2);
                startActivity(intent);
            }

        });

    }

}
