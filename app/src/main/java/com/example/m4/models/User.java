package com.example.m4.models;

/**
 * Created by sli474 on 2/12/17.
 */

public class User implements AccountType
{
    private String username;
    private String password;

    public User() {
        this("dummy_username", "dummy_password");
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValidPassword(String input) {
        return password.equals(input);
    }

}
