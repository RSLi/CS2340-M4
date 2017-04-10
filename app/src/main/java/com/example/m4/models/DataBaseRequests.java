package com.example.m4.models;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


@SuppressWarnings("ConstantConditions")
public class DataBaseRequests {

    //public static String url = "http://nstoltzfus3.pythonanywhere.com/";


    public static void registerUsers(final Context context){
        String myURL = "http://nstoltzfus3.pythonanywhere.com/getusers";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, myURL, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Toast out = Toast.makeText(context, "User Database Connection Successful", Toast.LENGTH_SHORT);
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

                                AccountType newAccount;

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

                                System.out.println("Username: " + username);
                                System.out.println("Password: " + password);
                                System.out.println("accountType: " + newAccount);

                                newAccount.setUsername(username);
                                newAccount.setPassword(password);
                                HashMap<String, String> profile = new HashMap<>();
                                profile.put("address", address);
                                profile.put("title", title);
                                profile.put("email", email);
                                newAccount.setProfileData(profile);


                                //boolean registerSuccess = Models.register(newAccount);

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

    public static void addUser(final Context context, final AccountType user){
        String myURL = "http://nstoltzfus3.pythonanywhere.com/adduser";

        //Map<String, String> params = new HashMap<>();

//        params.put("user_name", user.getUsername());
//        params.put("password", user.getPassword());
//        params.put("account_type", ((User)user).getType());
//        if (user.getProfileData() != null) {
//            params.put("address", user.getProfileData().get("address").toString());
//            params.put("title", user.getProfileData().get("title").toString());
//            params.put("email", user.getProfileData().get("email").toString());
//        } else {
//            params.put("address", "");
//            params.put("title", "");
//            params.put("email", "");
//        }
//
//        JSONObject jsonObj = new JSONObject(params);
        StringRequest stringRequest = new StringRequest
        (Request.Method.POST, myURL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast out = Toast.makeText(context, "User Successfully Added", Toast.LENGTH_SHORT);
                        out.show();
                        System.out.println(response);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast out = Toast.makeText(context, "User Unsuccessfully Added", Toast.LENGTH_SHORT);
                        out.show();
                        // TODO Auto-generated method stub

                    }
                }){
                    @Override
                    protected Map<String,String> getParams(){
                        Map<String, String> params = new HashMap<>();
                        params.put("user_name", user.getUsername());
                        params.put("password", user.getPassword());
                        params.put("account_type", ((User)user).getType());
                        if (user.getProfileData() != null) {
                            params.put("address", user.getProfileData().get("address").toString());
                            params.put("title", user.getProfileData().get("title").toString());
                            params.put("email", user.getProfileData().get("email").toString());
                        } else {
                            params.put("address", "");
                            params.put("title", "");
                            params.put("email", "");
                        }

                        return params;
                    }

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        HashMap<String, String> headers = new HashMap<>();
                        headers.put("Content-Type", "application/x-www-form-urlencoded");
                        //headers.put("Content-Type", "application/json; charset=utf-8");
                        return headers;
                    }

                    @Override
                    public String getBodyContentType() {
                        return "application/json";
                    }

        };
        System.out.println("Object Request:  " + stringRequest.toString());
        Log.d("Debug", "Object Request:  " + stringRequest.toString());
        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }





    public static void getReports(final Context context){
        String myURL = "http://nstoltzfus3.pythonanywhere.com/getreports";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, myURL, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Toast out = Toast.makeText(context, "Report Database Connection Successful", Toast.LENGTH_SHORT);
                        out.show();
                        try {

                            JSONArray allReports = response.getJSONArray("allReports");
                            if (allReports == null) {
                                System.out.println("All reports are null");
                            }

                            for (int i = 0; i < allReports.length(); i++) {
                                JSONObject report = (JSONObject) allReports.get(i);
                                if (report == null) {
                                    System.out.println("This report is null");
                                }
                                String reportType = report.getString("report_type");
                                String waterCondition = report.getString("water_condition");
                                Double virusPPM = report.getDouble("virus_ppm");
                                Double contaminantPPM = report.getDouble("contaminant_ppm");
                                String waterType = report.getString("water_type");
                                String reporterUser = report.getString("reporter_username");
                                Integer reportNumber = report.getInt("report_number");
                                String dateString = report.getString("date");
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                                Integer year = report.getInt("year");
                                Integer month = report.getInt("month");
                                String location = report.getString("location");
                                Double latitude = report.getDouble("latitude");
                                Double longitude = report.getDouble("longitude");

                                System.out.println(waterCondition);
                                System.out.println(reporterUser);
                                System.out.println(reportNumber);
                                
                                boolean addReport;
                                switch (reportType) {
                                    case "Water Purity Report": {
                                        WaterPurityReport newReport = new WaterPurityReport();
                                        newReport.setWaterOverallCondition(WaterOverallCondition.valueOf(waterCondition));
                                        newReport.setVirusPPM(virusPPM);
                                        newReport.setContaminantPPM(contaminantPPM);
                                        newReport.setReporterUsername(reporterUser);
                                        newReport.setReportNumber(reportNumber);
                                        newReport.setDate(sdf.parse(dateString));
                                        newReport.setYear(year);
                                        newReport.setMonth(month);
                                        newReport.setLocation(location);
                                        newReport.setLatitude(latitude);
                                        newReport.setLongitude(longitude);

                                        addReport = Models.submitReport(newReport);
                                        System.out.println(addReport);


                                        break;
                                    }
                                    case "Water Source Report": {
                                        WaterSourceReport newReport = new WaterSourceReport();
                                        newReport.setWaterCondition(WaterCondition.valueOf(waterCondition));
                                        newReport.setWaterType(WaterType.valueOf(waterType));
                                        newReport.setReporterUsername(reporterUser);
                                        newReport.setReportNumber(reportNumber);
                                        newReport.setDate(sdf.parse(dateString));
                                        newReport.setYear(year);
                                        newReport.setMonth(month);
                                        newReport.setLocation(location);
                                        newReport.setLatitude(latitude);
                                        newReport.setLongitude(longitude);

                                        addReport = Models.submitReport(newReport);
                                        System.out.println(addReport);


                                        break;
                                    }
                                    default:
                                        System.out.println("No valid report");
                                        System.out.println(false);

                                        break;
                                }

                            }



                        } catch (Exception e) {
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

    public static void createPurityReport(final Context context, final WaterPurityReport waterPurity){
        String myURL = "http://nstoltzfus3.pythonanywhere.com/addreport";

        StringRequest stringRequest = new StringRequest
                (Request.Method.POST, myURL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast out = Toast.makeText(context, "Report Successfully Added", Toast.LENGTH_SHORT);
                        out.show();
                        System.out.println(response);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast out = Toast.makeText(context, "Report Unsuccessfully Added", Toast.LENGTH_SHORT);
                        out.show();
                        // TODO Auto-generated method stub

                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("report_number", Integer.toString(waterPurity.getReportNumber()));
                params.put("report_type", "Water Purity Report");
                params.put("water_condition", waterPurity.getWaterOverallCondition().toString());
                params.put("virus_ppm", waterPurity.getVirusPPM().toString());
                params.put("contaminant_ppm", waterPurity.getContaminantPPM().toString());
                params.put("water_type", "None");
                params.put("reporter_username", waterPurity.getReporterUsername());
                params.put("date", waterPurity.getDate().toString());
                params.put("year", Integer.toString(waterPurity.getYear()));
                params.put("month", Integer.toString(waterPurity.getMonth()));
                params.put("location", Double.toString(waterPurity.getLatitude()) + ',' + Double.toString(waterPurity.getLongitude()));
                params.put("latitude", Double.toString(waterPurity.getLatitude()));
                params.put("longitude", Double.toString(waterPurity.getLongitude()));

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/x-www-form-urlencoded");
                //headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }

        };
        System.out.println("Object Request:  " + stringRequest.toString());
        Log.d("Debug", "Object Request:  " + stringRequest.toString());
        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    public static void createSourceReport(final Context context, final WaterSourceReport waterSource){
        String myURL = "http://nstoltzfus3.pythonanywhere.com/addreport";

        StringRequest stringRequest = new StringRequest
                (Request.Method.POST, myURL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast out = Toast.makeText(context, "Report Successfully Added", Toast.LENGTH_SHORT);
                        out.show();
                        System.out.println(response);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast out = Toast.makeText(context, "Report Unsuccessfully Added", Toast.LENGTH_SHORT);
                        out.show();
                        // TODO Auto-generated method stub

                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("report_type", "Water Source Report");
                params.put("report_number", Integer.toString(waterSource.getReportNumber()));
                params.put("water_condition", waterSource.getWaterCondition().toString());
                params.put("virus_ppm", "None");
                params.put("contaminant_ppm", "None");
                params.put("water_type", waterSource.getWaterType().toString());
                params.put("reporter_username", waterSource.getReporterUsername());
                params.put("date", waterSource.getDate().toString());
                params.put("year", Integer.toString(waterSource.getYear()));
                params.put("month", Integer.toString(waterSource.getMonth()));
                params.put("location", Double.toString(waterSource.getLatitude()) + ',' + Double.toString(waterSource.getLongitude()));
                params.put("latitude", Double.toString(waterSource.getLatitude()));
                params.put("longitude", Double.toString(waterSource.getLongitude()));

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/x-www-form-urlencoded");
                //headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }

        };
        System.out.println("Object Request:  " + stringRequest.toString());
        Log.d("Debug", "Object Request:  " + stringRequest.toString());
        MySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

}
