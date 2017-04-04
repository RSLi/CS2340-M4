package com.example.m4.models;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by nickstoltz on 4/4/17.
 */

public class DataBaseRequests {

    public String url = "http://nstoltzfus3.pythonanywhere.com/";


    public void getUsers(final Context context){
        String myURL = url + "getusers";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Toast out = Toast.makeText(context, "Database Connection Successful", Toast.LENGTH_SHORT);
                            out.show();

                            JSONArray allUsers = response.getJSONArray("allUsers");

                            for (int i = 0; i < allUsers.length(); i++) {
                                JSONObject user = (JSONObject) allUsers.get(i);
                                String username = user.getString("user_name");
                                String password = user.getString("password");
                                String address = user.getString("address");
                                String title = user.getString("title");
                                String email = user.getString("email");
                                String accountType = user.getString("account_type");

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

                                System.out.println("Username: " + username);
                                System.out.println("Password: " + password);
                                System.out.println("accountType: " + newAccount);

                                newAccount.setUsername(username);
                                newAccount.setPassword(password);


                                boolean registerSuccess = Models.register(newAccount);

                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast out = Toast.makeText(context, "Database Connection Failed", Toast.LENGTH_SHORT);
                        out.show();
                        // TODO Auto-generated method stub

                    }
                });
        MySingleton.getInstance(context).addToRequestQueue(jsObjRequest);
    }
}
