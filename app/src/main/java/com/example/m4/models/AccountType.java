package com.example.m4.models;

import java.util.HashMap;

public interface AccountType
{
    /**
     * Get the account username
     * @return The username of the Account
     */
    String getUsername();

    /**
     * Get the account password
     * @return The password of the Account
     */
    String getPassword();

    /**
     * Set the username of the account
     * @param username The new username desired
     */
    void setUsername(String username);

    /**
     * Set the password of the account
     * @param password The new password desired
     */
    void setPassword(String password);

    /**
     * Get a Map storing user profile data in key value pairs
     * @return  a HashMap storing user profile data in key value pairs
     */
    HashMap getProfileData();

    /**
     * Set new profile data to the user
     * @param newData desired new profile in key value pairs
     */
    void setProfileData(HashMap newData);

    /**
     * Check if the account possess a certain permission
     * @param permission the permission needs to be checked
     * @return true if the account has the permission, false otherwise
     */
    boolean hasPermission(Permission permission);
}
