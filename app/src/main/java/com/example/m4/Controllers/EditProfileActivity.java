package com.example.m4.Controllers;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.m4.R;
import com.example.m4.models.Models;

import java.util.HashMap;
import java.util.regex.Pattern;

public class EditProfileActivity extends AppCompatActivity
{
    EditText mFieldProfileEmail;
    EditText mFieldProfileAddress;
    EditText mFieldProfileTitle;
    Button mBtnSaveProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        //set up view's variables
        mFieldProfileEmail = (EditText) findViewById(R.id.field_profile_email);
        mFieldProfileAddress = (EditText) findViewById(R.id.field_profile_address);
        mFieldProfileTitle = (EditText) findViewById(R.id.field_profile_title);
        mBtnSaveProfile = (Button) findViewById((R.id.btn_save_profile));

        //once submit is clicked, new info is added to the hashmap
        mBtnSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap newProfileData = new HashMap();
                Boolean ifSave = true;
                if (isValidEmail(mFieldProfileEmail.getText().toString())) {
                    newProfileData.put("email", mFieldProfileEmail.getText().toString());
                } else {
                    new AlertDialog.Builder(EditProfileActivity.this)
                            .setTitle("Error!")
                            .setMessage("Please entry valid email address").show();
                    ifSave = false;
                }
                newProfileData.put("address", mFieldProfileAddress.getText().toString());
                newProfileData.put("title", mFieldProfileTitle.getText().toString());
                if (ifSave) {
                    Models.accountInSession.setProfileData(newProfileData);
                    Intent intent = new Intent(EditProfileActivity.this, ViewProfileActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        });

        //Cancel button
        Button btnGotoEdit = (Button) findViewById(R.id.btn_cancel_edit);
        btnGotoEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(EditProfileActivity.this, ViewProfileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        //display existing user info
        HashMap profileData = Models.accountInSession.getProfileData();
        if (profileData != null) {
            mFieldProfileEmail.setText((String) profileData.get("email"));
            mFieldProfileTitle.setText((String) profileData.get("title"));
            mFieldProfileAddress.setText((String) profileData.get("address"));

        }
    }
    private static final Pattern EMAIL = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );

    public static boolean isValidEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException();
        } else {
            return EMAIL.matcher(email).matches();
        }
    }
}
