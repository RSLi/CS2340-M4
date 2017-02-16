package com.example.m4;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by yuxin on 2/15/17.
 */

public class LoginFailure extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        TextView textElement = (TextView) findViewById(R.id.this_is_id_name);
        textElement.setText("Login failed");

        Button btnGotoLogin = (Button)findViewById(R.id.btn_goto_login);
        Button btnGotoRegistration = (Button)findViewById(R.id.btn_goto_registration);
        btnGotoLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToLogin();
            }
        });
        btnGotoRegistration.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToRegister();
            }
        });
}
