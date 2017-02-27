package com.example.m4.models;

import java.util.ArrayList;
import java.util.HashMap;

public class Models
{
    private static HashMap localAccounts = new HashMap<String, AccountType>();
    private static AccountType accountInSession;

    public static HashMap getLocalAccounts() {
        return localAccounts;
    }

    public static AccountType getAccountInSession() {
        return accountInSession;
    }

    public static boolean register(AccountType newAccount) {
        //TODO: register the user in the localAccounts map
        localAccounts.put(newAccount.getUsername(),newAccount);
        return true;
    }
}
