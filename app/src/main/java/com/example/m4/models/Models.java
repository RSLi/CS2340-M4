package com.example.m4.models;

import java.util.ArrayList;
import java.util.HashMap;

public class Models
{
    public static HashMap<String, AccountType> localAccounts = new HashMap<String, AccountType>();
    public static AccountType accountInSession;

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
    public static boolean login(String username, String pass) {
        if (localAccounts.containsKey(username)) {
            if (localAccounts.get(username).getPassword().equals(pass)) {
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

}
