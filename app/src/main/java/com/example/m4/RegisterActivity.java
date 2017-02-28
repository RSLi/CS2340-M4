package com.example.m4;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
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

import com.example.m4.models.AccountType;
import com.example.m4.models.Administrator;
import com.example.m4.models.Manager;
import com.example.m4.models.Models;
import com.example.m4.models.User;
import com.example.m4.models.Worker;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class RegisterActivity extends AppCompatActivity {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo:hello", "bar:world"
    };


    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
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

        Button mEmailSignInButton = (Button) findViewById(R.id.btn_register);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegister();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    /**
     * Registration Logic when clicking register button
     */
    private void attemptRegister() {
        //TODO: Better Validation in the future
        String username = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        String accountType = mAccountTypeSpinner.getSelectedItem().toString();
        AccountType newAccount = new User();

        if (accountType.equals("Administrator")) {
            newAccount = new Administrator();
        } else if (accountType.equals("Worker")) {
            newAccount = new Worker();
        } else if (accountType.equals("Manager")) {
            newAccount = new Manager();
        } else {
            newAccount = new User();
        }

        newAccount.setUsername(username);
        newAccount.setPassword(password);

        boolean registerSuccess = Models.register(newAccount);

        // Make an alert dialog to indicate registration status
        if (registerSuccess) {
            new AlertDialog.Builder(RegisterActivity.this)
                    .setTitle("Register Success")
                    .setMessage("Go to login screen to proceed").show();
        } else {
            new AlertDialog.Builder(RegisterActivity.this)
                    .setTitle("Register Failure")
                    .setMessage("Please try different username/password and then proceed").show();
        }
    }

}

