package com.example.m4.models;

import java.util.Calendar;
import java.util.Date;

/**
 * Report object containing common report information
 */
public abstract class Report {
    private String reporterUsername;
    private int reportNumber;
    private Date date;
    private int year;
    private int month;
    private String location;
    private double latitude;
    private double longitude;

    public String getReporterUsername() {
        return reporterUsername;
    }

    public void setReporterUsername(String username) {
        this.reporterUsername = username;
    }

    public int getReportNumber() {
        return reportNumber;
    }

    public void setReportNumber(int reportNumber) {
        this.reportNumber = reportNumber;
    }

    public Date getDate() { return date;}

    public void setDate(Date date) {
        this.date = date;
    }

    public void setYear(int year) {this.year = year;}

    public int getYear() {return year;}

    public int getMonth() {return month;}

    public void setMonth(int month) {this.month = month;}

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String toString() {
        return "Report Number: " + getReportNumber();
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Check if the data in the report is valid
     * @return true if data is valid to be inserted
     */
    abstract public boolean isValid();

}
