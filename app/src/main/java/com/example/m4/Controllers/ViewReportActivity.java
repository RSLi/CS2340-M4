package com.example.m4.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.m4.R;
import com.example.m4.models.Models;
import com.example.m4.models.Permission;

/**
 * Created by yuchen on 2017/3/14.
 */

public class ViewReportActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_report_options);

        Button btnViewReportList = (Button) findViewById(R.id.btn_source_report_list);

        //add permission to view source reports
        btnViewReportList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Models.getAccountInSession().hasPermission(Permission.ACCESS_SOURCE_REPORT)) {
                    Intent intent = new Intent(ViewReportActivity.this, ViewSourceReportListActivity.class);
                    startActivity(intent);
                } else {
                    new AlertDialog.Builder(ViewReportActivity.this)
                            .setTitle("No Permission")
                            .setMessage("Permission denied for current user").show();
                }

            }
        });

        Button btnViewPurityReportList = (Button) findViewById(R.id.btn_purity_report_list);
        //add permission that only manager can view purity reports
        btnViewPurityReportList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Models.getAccountInSession().hasPermission(Permission.ACCESS_PURITY_REPORT)) {
                    Intent intent = new Intent(ViewReportActivity.this, ViewPurityReportListActivity.class);
                    startActivity(intent);
                } else {
                    new AlertDialog.Builder(ViewReportActivity.this)
                            .setTitle("No Permission")
                            .setMessage("Only Managers can view Purity Reports").show();
                }

            }
        });

        Button btnViewHistoryReport = (Button) findViewById(R.id.btn_historical_report);
        //add permission that only manager can view historical reports
        btnViewHistoryReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Models.getAccountInSession().hasPermission(Permission.ACCESS_HISTORICAL_REPORT)) {
                    Intent intent = new Intent(ViewReportActivity.this, HistoricalReportFilter.class);
                    startActivity(intent);
                } else {
                    new AlertDialog.Builder(ViewReportActivity.this)
                            .setTitle("No Permission")
                            .setMessage("Only Managers can view Historical Reports").show();
                }

            }
        });

        Button btnViewWaterAvail = (Button) findViewById(R.id.btn_water_availability);
        //set buttons
        btnViewWaterAvail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewReportActivity.this, ViewReportsMapActivity.class);
                startActivity(intent);
            }
        });
    }
}
