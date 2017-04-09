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
import com.example.m4.models.WaterSourceReport;

import java.util.ArrayList;

public class ViewSourceReportListActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_report_list);
        //add all the source reports into a list
        // if in API 24+:
        // final ArrayList<Report> profileData = Models.getReportsAsList().stream().filter(report -> report instanceof WaterSourceReport).collect(Collectors.toList());
        //noinspection unchecked
        ArrayList<Report> allReportList = Models.getReportsAsList();
        final ArrayList<WaterSourceReport> profileData = new ArrayList<WaterSourceReport>();
        for (Report report : allReportList) {
            if (report instanceof WaterSourceReport) {
                profileData.add((WaterSourceReport) report);
            }
        }
        // display list of source reports
        final ListView listview = (ListView) findViewById(R.id.report_list);
        //noinspection unchecked
        final ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, profileData);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                //view one report
                Intent intent = new Intent(ViewSourceReportListActivity.this, ViewOneReport.class);
                intent.putExtra("rowNum", arg2);
                intent.putExtra("reportNo", profileData.get(arg2).getReportNumber());
                startActivity(intent);
            }

        });

    }

}
