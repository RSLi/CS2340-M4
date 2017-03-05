package com.example.m4.models;

import java.util.Date;

/**
 * Report object containing common report information
 */
public abstract class Report
{
    String reporterUsername;
    int reportNumber;
    Date date;
    String location;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Check if the data in the report is valid
     * @return true if data is valid to be inserted
     */
    abstract public boolean isValid();

}
