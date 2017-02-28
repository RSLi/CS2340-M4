package com.example.m4.models;

/**
 * TODO: Probably need to be refactored based on new agreements on class relationships
 */

public class Administrator extends User
{
    private String username;
    private String password;

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
}
