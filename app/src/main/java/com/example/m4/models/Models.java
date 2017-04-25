package com.example.m4.models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;


public class Models
{
    private static final HashMap<String, AccountType> localAccounts = new HashMap<>();
    public static AccountType accountInSession;
    private static final ArrayList<Report> localReportList = new ArrayList<>();

    public static AccountType getAccountInSession() {
        return accountInSession;
    }

    /**
     * Register the user in temporary storage
     * @param newAccount new AccountType to be registered
     * @return true if registration is successful
     */
    @SuppressWarnings("SameReturnValue")
    public static boolean register(AccountType newAccount) {
//        if (newAccount.getUsername() == null) {
//            return false;
//        } else if (newAccount.getPassword() == null) {
//            return false;
//        } else if (newAccount.getUsername().length() > 20) {
//            return false;
//        } else if (newAccount.getPassword().length() > 20) {
//            return false;
//        } else if (newAccount.getUsername().length() < 4) {
//            return false;
//        } else if (newAccount.getPassword().length() < 8) {
//            return false;
//        }
        localAccounts.put(newAccount.getUsername(),newAccount);
        return true;
    }

    /**
     * Login user in session
     * @param username The username of the account to be logged in
     * @param pass The password of the account to be logged in
     * @return true if login successful, false otherwise
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
     *
     */
    public static void logout() {
        accountInSession = null;
    }

    public static ArrayList getReportsAsList() {
        return localReportList;
    }
    /**
     * Add report
     * @param report The report want to submit
     * @throws RuntimeException account not logged in
     * @return false if report data is missing or invalid
     */
    public static boolean submitReport(Report report) {
        if (accountInSession == null) {
            throw new RuntimeException("Should not submit report when not logged in");
        }

        report.setDate(new Date());
        report.setYear(Calendar.getInstance().get(Calendar.YEAR));

        //For testing for other month, comment this line
        report.setMonth(Calendar.getInstance().get(Calendar.MONTH));
        report.setReportNumber(localReportList.size()); //TODO: Change to backend real index
        report.setReporterUsername(Models.accountInSession.getUsername());
        if (!report.isValid()) {
            return false;
        }
        localReportList.add(report);

        return true;
    }

}
