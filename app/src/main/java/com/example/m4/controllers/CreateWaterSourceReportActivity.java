package com.example.m4.controllers;

import android.Manifest;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.m4.R;
import com.example.m4.models.DataBaseRequests;
import com.example.m4.models.Models;
import com.example.m4.models.WaterCondition;
import com.example.m4.models.WaterSourceReport;
import com.example.m4.models.WaterType;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import java.util.Date;
import java.util.Locale;

public class CreateWaterSourceReportActivity extends AppCompatActivity
        implements ConnectionCallbacks, OnConnectionFailedListener {
    private final WaterSourceReport report = new WaterSourceReport();

    private GoogleApiClient mGoogleApiClient;

    private EditText mLongitudeEt;
    private EditText mLatitudeEt;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_water_source_report);
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        TextView text1 = (TextView) findViewById(R.id.textView26);
        text1.startAnimation(animation);
        TextView text2 = (TextView) findViewById(R.id.textView28);
        text2.startAnimation(animation);

        //get buttons
        Button btnSourceReportSubmit = (Button) findViewById(R.id.btn_source_submit);
        Button btnSourceReportCancel = (Button) findViewById(R.id.btn_source_cancel);

        //set up waterType and waterCondition spinners
        final Spinner spWaterType = (Spinner) findViewById(R.id.spinner_water_type);
        final Spinner spWaterCondition = (Spinner) findViewById(R.id.spinner_condition);
        spWaterType.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, WaterType.values()));
        spWaterCondition.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, WaterCondition.values()));
        //get info user typed in
        final EditText longitude = (EditText) findViewById(R.id.edit_longitude);
        final EditText latitude = (EditText) findViewById(R.id.edit_latitude);

        mLongitudeEt = longitude;
        mLatitudeEt = latitude;
        LatLng latLng = ViewReportsMapActivity.getLocationCache();
        if (latLng != null) {
            mLongitudeEt.setText(Double.toString(latLng.longitude));
            mLatitudeEt.setText(Double.toString(latLng.latitude));
        }

        //display reporter name, report number, and dateTime on screen by auto-generation
        TextView reporter = (TextView) findViewById((R.id.field_source_reporter));
        TextView reportNo = (TextView) findViewById((R.id.field_source_num));
        TextView dateTime = (TextView) findViewById((R.id.field_date_time));
        reporter.setText(Models.accountInSession.getUsername());
        reportNo.setText((String.format(Locale.US, "%d", Models.getReportsAsList().size())));
        Date date = new Date();
        dateTime.setText(date.toString());

        //new report should be created once click submit button
        btnSourceReportSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                report.setWaterCondition((WaterCondition)spWaterCondition.getSelectedItem());
                report.setWaterType((WaterType)spWaterType.getSelectedItem());
                report.setLongitude(Double.parseDouble(longitude.getText().toString()));
                report.setLatitude(Double.parseDouble(latitude.getText().toString()));
                Models.submitReport(report);
                addSourceDB();

                Intent intent = new Intent(CreateWaterSourceReportActivity.this, CreateReportActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                sendNotification();
            }
        });

        //set up cancel button
        btnSourceReportCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateWaterSourceReportActivity.this, CreateReportActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        initGoogleApiClient();

        Button autoLocationBtn = (Button) findViewById(R.id.btn_auto_location);
        autoLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autoLocation();
            }
        });

        checkLocationPermission();

    }
    private void addSourceDB() {
        DataBaseRequests.createSourceReport(this, report);
    }

    private void checkLocationPermission() {
        int res = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (res != PackageManager.PERMISSION_GRANTED) {
            String[] premissions = {Manifest.permission.ACCESS_FINE_LOCATION};
            ActivityCompat.requestPermissions(this, premissions, 1);
        }
    }


    private void autoLocation() {
        //check device location permission
        int res = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (res == PackageManager.PERMISSION_GRANTED) {
            Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            if (location != null) {
                mLongitudeEt.setText(Double.toString(location.getLongitude()));
                mLatitudeEt.setText(Double.toString(location.getLatitude()));
            } else {
                Toast.makeText(this, "Get Location Failed!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Permission Denied,Get Location Failed!", Toast.LENGTH_SHORT).show();
        }
    }

    private void initGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mGoogleApiClient != null) {
            if (mGoogleApiClient.isConnected()) {
                mGoogleApiClient.disconnect();
            }
            mGoogleApiClient.unregisterConnectionCallbacks(this);
            mGoogleApiClient.unregisterConnectionFailedListener(this);
            mGoogleApiClient = null;
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.d("CWSReportActivity", "GoogleApiClient connect success");
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d("CWSReportActivity", "GoogleApiClient connect suspend");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("CWSReportActivity", "GoogleApiClient connect failed");
    }

    private void sendNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        // set notification icon title text;
        builder.setSmallIcon(R.drawable.logo1);
        builder.setContentTitle("New water source found! Click to see details.");
        builder.setContentText("New water source found! Click to see details.");

        // set notification click event
        Intent intent = new Intent(this, ViewReportActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        builder.setAutoCancel(true);

        // notification id should be same
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());
    }
}
