package com.example.m4.models;

public interface AccountType
{
    public boolean isValid();
    public String getUsername();
    public String getPassword();
    public void setUsername(String username);
    public void setPassword(String password);
}
