package com.example.m4.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Models
{
    public static HashMap<String, AccountType> localAccounts = new HashMap<String, AccountType>();
    public static AccountType accountInSession;
    public static ArrayList<Report> localReportList = new ArrayList<Report>();
    public static ArrayList<Report> fullReport = new ArrayList<Report>();

    public static HashMap getLocalAccounts() {
        return localAccounts;
    }

    public static AccountType getAccountInSession() {
        return accountInSession;
    }

    /**
     * Register the user in temporary storage
     * @param newAccount
     * @return
     */
    public static boolean register(AccountType newAccount) {
        localAccounts.put(newAccount.getUsername(),newAccount);
        return true;
    }

    /**
     * Login user in session
     * @param username
     * @param pass
     * @return
     */
    public static boolean login(String username, String pass)
    {
        if (localAccounts.containsKey(username))
        {
            if (localAccounts.get(username).getPassword().equals(pass))
            {
                accountInSession = localAccounts.get(username);
                return true;
            }
        }
        return false;
    }

    /**
     * Clear session variable and log user out
     * @return
     */
    public static boolean logout() {
        accountInSession = null;
        return true;
    }

    public static ArrayList getReportsAsList() {
        return localReportList;
    }
    /**
     * Add report
     * @param report
     * @throws RuntimeException account not logged in
     * @return false if report data is missing or invalid
     */
    public static boolean submitReport(Report report) {
        if (accountInSession == null) {
            throw new RuntimeException("Should not submit report when not logged in");
        }

        report.setDate(new Date());
        report.setReportNumber(localReportList.size()); //TODO: Change to backend real index
        report.setReporterUsername(Models.accountInSession.getUsername());
        if (!report.isValid()) {
            return false;
        }
        localReportList.add(report);

        return true;
    }

}
