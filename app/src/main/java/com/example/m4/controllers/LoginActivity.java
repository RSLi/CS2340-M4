package com.example.m4.controllers;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.example.m4.R;
import com.example.m4.models.Models;

/**
 * A login screen that offers login via username/password.
 */
public class LoginActivity extends AppCompatActivity
{
    // UI references.
    private AutoCompleteTextView mUsernameView;
    private EditText mPasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mUsernameView = (AutoCompleteTextView) findViewById(R.id.username);
        mPasswordView = (EditText) findViewById(R.id.password);

        Button mBtnSignIn = (Button) findViewById(R.id.btn_sign_in);
        mBtnSignIn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String inputUsername = mUsernameView.getText().toString();
                String inputPass = mPasswordView.getText().toString();


                if (Models.login(inputUsername, inputPass)) {
                    Intent intent = new Intent(LoginActivity.this, MainAppActivity.class);
                    startActivity(intent);
                } else {
                    new AlertDialog.Builder(LoginActivity.this)
                            .setTitle("Wrong Credential")
                            .setMessage("Wrong Username or Password").show();

                }
            }
        });
        //button for cancelling registration
        Button btnCancelLogin = (Button)findViewById(R.id.btn_cancelLogin);
        btnCancelLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cancelLogin();
            }
        });
    }

    private void cancelLogin() {
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }
}

