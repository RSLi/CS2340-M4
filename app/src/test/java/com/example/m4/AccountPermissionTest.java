package com.example.m4;


import com.example.m4.models.AccountType;
import com.example.m4.models.Manager;
import com.example.m4.models.Permission;
import com.example.m4.models.User;
import com.example.m4.models.Worker;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test permissions of all regular accounts including User, Worker and Manager
 * @author Siwei "Robert" Li
 */
public class AccountPermissionTest
{

    /**
     * Test for checking User's permission
     */
    @Test
    public void testUserPermission() {
        AccountType user = new User();
        assertFalse(user.hasPermission(Permission.SUBMIT_REPORT));
        assertFalse(user.hasPermission(Permission.ACCESS_HISTORICAL_REPORT));
        assertFalse(user.hasPermission(Permission.ACCESS_PURITY_REPORT));
        assertFalse(user.hasPermission(Permission.DELETE_REPORT));
        assertTrue(user.hasPermission(Permission.ACCESS_SOURCE_REPORT));
        assertTrue(user.hasPermission(Permission.ACCESS_AVAILABILITY_REPORT));
    }

    /**
     * Test for checking Worker's permission
     */
    @Test
    public void testWorkerPermission() {
        AccountType worker = new Worker();
        assertFalse(worker.hasPermission(Permission.DELETE_REPORT));
        assertFalse(worker.hasPermission(Permission.ACCESS_HISTORICAL_REPORT));
        assertFalse(worker.hasPermission(Permission.ACCESS_PURITY_REPORT));
        assertTrue(worker.hasPermission(Permission.ACCESS_SOURCE_REPORT));
        assertTrue(worker.hasPermission(Permission.ACCESS_AVAILABILITY_REPORT));
        assertTrue(worker.hasPermission(Permission.SUBMIT_REPORT));
    }

    /**
     * Test for checking Manager's permission
     */
    @Test
    public void testManagerPermission() {
        AccountType manager = new Manager();
        assertTrue(manager.hasPermission(Permission.DELETE_REPORT));
        assertTrue(manager.hasPermission(Permission.ACCESS_HISTORICAL_REPORT));
        assertTrue(manager.hasPermission(Permission.ACCESS_PURITY_REPORT));
        assertTrue(manager.hasPermission(Permission.ACCESS_SOURCE_REPORT));
        assertTrue(manager.hasPermission(Permission.ACCESS_AVAILABILITY_REPORT));
        assertTrue(manager.hasPermission(Permission.SUBMIT_REPORT));
    }


}
