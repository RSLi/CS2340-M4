package com.example.m4.models;


import java.util.Arrays;
import java.util.List;

public class Manager extends Worker
{
    // add permissions for managers
    private List<Permission> permissions = Arrays.asList(
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
}
