package com.example.m4.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.m4.R;

/**
 * Created by yuchen on 2017/3/14.
 */

public class ViewReportActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_report_options);

        Button btnViewReportList = (Button) findViewById(R.id.btn_report_list);
        //set buttons
        btnViewReportList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewReportActivity.this, ViewReportListActivity.class);
                startActivity(intent);
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
