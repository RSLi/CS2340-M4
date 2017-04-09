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

        //get the year and location from last intent
        Intent mIntent = getIntent();
        int year = mIntent.getIntExtra("Year", 0);
        String location = mIntent.getStringExtra("Location");

        //get all reports
        //noinspection unchecked
        ArrayList<Report> allReportList = Models.getReportsAsList();
        final ArrayList<WaterPurityReport> historyReport = new ArrayList<>();

        //for all reports, choose the ones that match the year and location selected by managers
        for (Report r: allReportList) {
            String rLocation = "" + r.getLongitude() + ", " + r.getLatitude();
            if (r instanceof WaterPurityReport && rLocation.equals(location) && r.getYear() == year) {
                historyReport.add((WaterPurityReport)r);
            }
        }

        //create a graph
        GraphView graph = (GraphView) findViewById(R.id.graph);
        //int size = historyReport.size();
        //create datapoint lists
        DataPoint[] virusList = new DataPoint[13];
        DataPoint[] contamList = new DataPoint[13];
        //add (0,0) to the datapoint lists for month 0(invalid)
        virusList[0] = new DataPoint(0,0);
        contamList[0] = new DataPoint(0,0);
        DataPoint vdp;
        DataPoint cdp;
        double virusTotal = 0;
        double contamTotal = 0;

        //Add datapoints to the graph.
        //The system will display an XY graph where the X axis is the month and the Y axis is the PPM.
        // Each month's data point can be an average of the reports for that month if there are more than one.
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
            //if there are no reports in this month
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
        //draw VirusPPM line and set up some attributes
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(virusList);
        series.setTitle("Virus PPM");
        series.setColor(Color.RED);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(12);
        series.setThickness(10);
        graph.addSeries(series);

        //set up the coordinate view
        GridLabelRenderer gridLabel = graph.getGridLabelRenderer();
        gridLabel.setHorizontalAxisTitle("Month");
        gridLabel.setNumHorizontalLabels(13);
        gridLabel.setPadding(40); // should allow for 3 digits to fit on screen

        //draw ContaminantPPM line and set up some attributes
        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(contamList);
        series2.setTitle("Contaminant PPM");
        series2.setColor(Color.BLUE);
        series2.setDrawDataPoints(true);
        series2.setDataPointsRadius(9);
        series2.setThickness(6);
        graph.addSeries(series2);
        gridLabel.setVerticalAxisTitle("PPM");
        gridLabel.setVerticalAxisTitleTextSize((float)30);

        //set up the range for x-axis
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(12);
        graph.getViewport().setXAxisBoundsManual(true);
    }
}
