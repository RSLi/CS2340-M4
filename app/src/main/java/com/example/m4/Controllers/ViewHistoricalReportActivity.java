package com.example.m4.Controllers;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.m4.R;
import com.example.m4.models.Models;
import com.example.m4.models.Report;
import com.example.m4.models.WaterPurityReport;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

/**
 * Created by yuchen on 2017/4/1.
 */

public class ViewHistoricalReportActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_report);
        Intent mIntent = getIntent();
        //get the year and location from last intent
        int year = mIntent.getIntExtra("Year", 0);
        String location = mIntent.getStringExtra("Location");
        ArrayList<Report> allReportList = Models.getReportsAsList();
        final ArrayList<WaterPurityReport> historyReport = new ArrayList<>();
        for (Report r: allReportList) {
            String rLocation = "" + r.getLongitude() + ", " + r.getLatitude();
            if (r instanceof WaterPurityReport && rLocation.equals(location) && r.getYear() == year) {
                historyReport.add((WaterPurityReport)r);
            }
        }

        GraphView graph = (GraphView) findViewById(R.id.graph);
        //graph.get
        //int size = historyReport.size();
        DataPoint[] virusList = new DataPoint[13];
        DataPoint[] contamList = new DataPoint[13];
        virusList[0] = new DataPoint(0,0);
        contamList[0] = new DataPoint(0,0);
        DataPoint vdp;
        DataPoint cdp;
        double virusTotal = 0;
        double contamTotal = 0;
        ArrayList<WaterPurityReport> d = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            virusTotal = 0;
            contamTotal = 0;
            //count = 0;
            for (WaterPurityReport r: historyReport) {
                if ((r.getMonth() + 1) == i ) {
                    virusTotal += r.getVirusPPM();
                    contamTotal += r.getContaminantPPM();
                    d.add(r);
                    //count += 1;
                }
            }
            if (virusTotal == 0 && contamTotal == 0) {
                virusList[i] = new DataPoint(i,0);
                contamList[i] = new DataPoint(i,0);
            } else {
                double avgVirusPPM = virusTotal / d.size();
                double avgContamPPM = contamTotal / d.size();
                vdp = new DataPoint(((double) (i)), avgVirusPPM);
                virusList[i] = vdp;
                cdp = new DataPoint(((double) (i)), avgContamPPM);
                contamList[i] = cdp;
            }
        }
        //draw VirusPPM line
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(virusList);
        series.setTitle("Virus PPM");
        series.setColor(Color.RED);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(10);
        series.setThickness(8);
        graph.addSeries(series);
        GridLabelRenderer gridLabel = graph.getGridLabelRenderer();
        gridLabel.setHorizontalAxisTitle("Month");
        gridLabel.setNumHorizontalLabels(13);
        gridLabel.setPadding(40); // should allow for 3 digits to fit on screen

        //draw ContaminantPPM line
        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(contamList);
        series2.setTitle("Contaminant PPM");
        series2.setColor(Color.BLUE);
        series2.setDrawDataPoints(true);
        series2.setDataPointsRadius(10);
        series2.setThickness(8);
        graph.addSeries(series2);
        gridLabel.setVerticalAxisTitle("PPM");
        gridLabel.setVerticalAxisTitleTextSize((float)30);

        //set up the range for x-axis
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(12);
        graph.getViewport().setXAxisBoundsManual(true);
    }
}
