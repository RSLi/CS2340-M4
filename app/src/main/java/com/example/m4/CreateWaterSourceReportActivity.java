package com.example.m4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.m4.models.Models;
import com.example.m4.models.WaterCondition;
import com.example.m4.models.WaterSourceReport;
import com.example.m4.models.WaterType;

import org.w3c.dom.Text;

import java.util.Date;

public class CreateWaterSourceReportActivity extends AppCompatActivity {
    final WaterSourceReport report = new WaterSourceReport();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_water_source_report);

        Button btnSourceReportSubmit = (Button) findViewById(R.id.btn_source_submit);
        Button btnSourceReportCancel = (Button) findViewById(R.id.btn_source_cancel);
        final Spinner spWaterType = (Spinner) findViewById(R.id.spinner_water_type);
        final Spinner spWaterCondition = (Spinner) findViewById(R.id.spinner_condition);
        spWaterType.setAdapter(new ArrayAdapter<WaterType>(this, android.R.layout.simple_spinner_item, WaterType.values()));
        spWaterCondition.setAdapter(new ArrayAdapter<WaterCondition>(this, android.R.layout.simple_spinner_item, WaterCondition.values()));
        final EditText location = (EditText) findViewById(R.id.edit_location);
        TextView reporter = (TextView) findViewById((R.id.field_source_reporter));
        TextView reportNo = (TextView) findViewById((R.id.field_source_num));
        TextView dateTime = (TextView) findViewById((R.id.field_date_time));

        reporter.setText(Models.accountInSession.getUsername());
        reportNo.setText(((Integer) Models.getReportsAsList().size()).toString());
        Date date = new Date();
        dateTime.setText(date.toString());

        btnSourceReportSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                report.setWaterCondition((WaterCondition)spWaterCondition.getSelectedItem());
                report.setWaterType((WaterType)spWaterType.getSelectedItem());
                report.setLocation(location.toString());
                Models.submitReport(report);
                Intent intent = new Intent(CreateWaterSourceReportActivity.this, CreateReportActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        btnSourceReportCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateWaterSourceReportActivity.this, CreateReportActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }
}
