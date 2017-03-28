package com.example.m4.models;

import java.util.HashMap;

public interface AccountType
{
    public boolean isValid();
    public String getUsername();
    public String getPassword();
    public void setUsername(String username);
    public void setPassword(String password);
    public HashMap getProfileData();
    public void setProfileData(HashMap newData);
    public boolean hasPermission(Permission permission);
}
