package com.example.m4.controllers;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.m4.R;
import com.example.m4.models.AccountType;
import com.example.m4.models.Administrator;
import com.example.m4.models.DataBaseRequests;
import com.example.m4.models.Manager;
import com.example.m4.models.Models;
import com.example.m4.models.User;
import com.example.m4.models.Worker;

/**
 * A login screen that offers login via email/password.
 */
public class RegisterActivity extends AppCompatActivity {

//    /**
//     * Id to identity READ_CONTACTS permission request.
//     */
//    //private static final int REQUEST_READ_CONTACTS = 0;
//
//    /**
//     * A dummy authentication store containing known user names and passwords.
//     * TODO: remove after connecting to a real authentication system.
//     */
//    private static final String[] DUMMY_CREDENTIALS = new String[]{
//            "foo:hello", "bar:world"
//    };


    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private Spinner mAccountTypeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.register_username);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptRegister();
                    return true;
                }
                return false;
            }
        });

        mAccountTypeSpinner = (Spinner) findViewById(R.id.spinner_account_type);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                                            R.array.account_type_array,
                                            android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mAccountTypeSpinner.setAdapter(adapter);
        //button for register
        Button mEmailSignInButton = (Button) findViewById(R.id.btn_register);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegister();
            }
        });

//        View mLoginFormView = findViewById(R.id.login_form);
//        View mProgressView = findViewById(R.id.login_progress);

        //button for cancelling registration
        Button btnGotoWelcome = (Button)findViewById(R.id.btn_goToWelcome);
        btnGotoWelcome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                gotoWelcome();
            }
        });
    }

    /**
     * Registration Logic when clicking register button
     */
    private void attemptRegister() {
        //TODO: Better Validation in the future
        String username = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        String accountType = mAccountTypeSpinner.getSelectedItem().toString();
        //noinspection UnusedAssignment
        AccountType newAccount = new User();

        switch (accountType) {
            case "Administrator":
                newAccount = new Administrator();
                break;
            case "Worker":
                newAccount = new Worker();
                break;
            case "Manager":
                newAccount = new Manager();
                break;
            default:
                newAccount = new User();
                break;
        }

        newAccount.setUsername(username);
        newAccount.setPassword(password);

        boolean registerSuccess = Models.register(newAccount);

        // Make an alert dialog to indicate registration status
        if (registerSuccess) {
            new AlertDialog.Builder(RegisterActivity.this)
                    .setTitle("Register Success")
                    .setMessage("Go to login screen to proceed").show();
            DataBaseRequests.addUser(this, newAccount);
        } else {
            new AlertDialog.Builder(RegisterActivity.this)
                    .setTitle("Register Failure")
                    .setMessage("Please try different username/password and then proceed").show();
        }
    }
    //method for going to the welcome page
    private void gotoWelcome() {
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }

}

