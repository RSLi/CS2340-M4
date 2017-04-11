package com.example.m4.models;

import java.util.Arrays;
import java.util.List;

/**
 * TODO: Probably need to be refactored based on new agreements on class relationships
 */

public class Administrator extends User
{
    private String username;
    private String password;

    // add permissions for managers
    private final List<Permission> permissions = Arrays.asList(
            Permission.ACCESS_SOURCE_REPORT,
            Permission.ACCESS_AVAILABILITY_REPORT,
            Permission.SUBMIT_REPORT,
            Permission.ACCESS_PURITY_REPORT,
            Permission.DELETE_REPORT,
            Permission.ACCESS_HISTORICAL_REPORT
    );

    @Override
    public boolean hasPermission(Permission permission) {
        return this.permissions.contains(permission);
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

    public String getType() {
        return "Administrator";
    }
}
