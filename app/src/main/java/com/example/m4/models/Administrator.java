package com.example.m4.models;

/**
 * Created by robertlee on 2/27/17.
 */

public class Administrator implements AccountType
{
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
