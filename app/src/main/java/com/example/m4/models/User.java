package com.example.m4.models;

import java.util.HashMap;

/**
 * Created by RSLi on 2/12/17.
 */

public class User implements AccountType
{
    private String username;
    private String password;
    private HashMap profileData;

    public User() {
        this("dummy_username", "dummy_password");
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValidPassword(String input) {
        return password.equals(input);
    }

    public HashMap getProfileData() {
        return profileData;
    }

    public void setProfileData(HashMap data) {
        this.profileData = data;
    }


}
