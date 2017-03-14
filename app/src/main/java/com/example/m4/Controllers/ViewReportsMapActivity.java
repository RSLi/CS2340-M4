package com.example.m4.Controllers;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.m4.R;
import com.example.m4.models.WaterPurityReport;
import com.example.m4.models.WaterSourceReport;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.example.m4.models.Models;
import com.example.m4.models.Report;

import java.util.List;

public class ViewReportsMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private List<Report> mReports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reports_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
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
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//        for (Report r : mReports) {
//            Marker markedReport = mMap.addMarker(new MarkerOptions().position(new LatLng(r.getLatitude(), r.getLongitude())));
//            if (r instanceof WaterPurityReport) {
//
//            } else if (r instanceof WaterSourceReport) {
//
//            } else {
//
//            }
//        }
    }
}
