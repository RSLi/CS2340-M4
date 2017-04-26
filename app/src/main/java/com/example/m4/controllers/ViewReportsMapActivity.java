package com.example.m4.controllers;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.example.m4.R;
import com.example.m4.models.WaterSourceReport;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.m4.models.Models;
import com.example.m4.models.Report;

import java.util.List;

public class ViewReportsMapActivity extends FragmentActivity implements OnMapReadyCallback {

    //SupportMapFragment fragMap;
    private static List<Report> mReports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reports_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        //noinspection unchecked
        mReports = Models.getReportsAsList();
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mReports = Models.getReportsAsList();
        //display markers for all water source reports
        for (Report r : mReports) {
            if (r instanceof WaterSourceReport) {
                LatLng location = new LatLng((r.getLongitude()), r.getLatitude());
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(location));
                //when the maker is clicked, show the water type and water condition of the water source
                googleMap.addMarker(new MarkerOptions().position(location).title("" + ((WaterSourceReport) r).getWaterType() + ", " + ((WaterSourceReport) r).getWaterCondition()));
            }
        }

        googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                showCreateReportDialog(latLng);
            }
        });
    }

    private void showCreateReportDialog(final LatLng latLng) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.title_create_report)
                .setMessage(R.string.message_create_report)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //save selected location
                        sLocationCache = latLng;
                        //start CreateReportActivity
                        Intent intent = new Intent(ViewReportsMapActivity.this,
                                CreateReportActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                }).setNegativeButton(R.string.cancel, null);
        builder.show();
    }

    private static LatLng sLocationCache = null;

    public static LatLng getLocationCache() {
        LatLng res = null;
        if (sLocationCache != null) {
            res = sLocationCache;
            sLocationCache = null;
        }
        return res;
    }

}
