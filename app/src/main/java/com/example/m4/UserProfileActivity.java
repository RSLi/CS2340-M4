package com.example.m4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.m4.models.Models;

import java.util.HashMap;

public class UserProfileActivity extends AppCompatActivity
{
    EditText mFieldProfileEmail;
    EditText mFieldProfileAddress;
    EditText mFieldProfileTitle;
    Button mBtnSaveProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        mFieldProfileEmail = (EditText) findViewById(R.id.field_profile_email);
        mFieldProfileAddress = (EditText) findViewById(R.id.field_profile_address);
        mFieldProfileTitle = (EditText) findViewById(R.id.field_profile_title);
        mBtnSaveProfile = (Button) findViewById((R.id.btn_save_profile));

        mBtnSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap newProfileData = new HashMap();
                newProfileData.put("email", mFieldProfileEmail.getText().toString());
                newProfileData.put("address", mFieldProfileAddress.getText().toString());
                newProfileData.put("title", mFieldProfileTitle.getText().toString());
                Models.accountInSession.setProfileData(newProfileData);

                Intent intent = new Intent(UserProfileActivity.this, MainAppActivity.class);
                startActivity(intent);
            }
        });

        HashMap profileData = Models.accountInSession.getProfileData();
        if (profileData != null) {
            mFieldProfileEmail.setText((String) profileData.get("email"));
            mFieldProfileTitle.setText((String) profileData.get("title"));
            mFieldProfileAddress.setText((String) profileData.get("address"));

        }
    }
}
