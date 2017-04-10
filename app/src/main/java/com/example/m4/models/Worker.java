package com.example.m4.models;

import java.util.Arrays;
import java.util.List;

public class Worker extends User
{
    // add permissions for workers
    private final List<Permission> permissions = Arrays.asList(
            Permission.ACCESS_SOURCE_REPORT,
            Permission.ACCESS_AVAILABILITY_REPORT,
            Permission.SUBMIT_REPORT
    );

    @Override
    public boolean hasPermission(Permission permission) {
        return this.permissions.contains(permission);
    }

    public String getType() {
        return "Worker";
    }
}
