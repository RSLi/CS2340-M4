package com.example.m4;

import com.example.m4.models.AccountType;
import com.example.m4.models.Models;
import com.example.m4.models.Worker;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by yuxin on 4/11/17.
 *
 * login test
 */

public class loginTest {

    private static final int TIMEOUT = 200;
    private AccountType account;

    /**
     * Initialize account
     */
    @Before
    public void initialize() {
        String username = "user";
        String password = "toBeOrNotNoBe";
        account = new Worker();
        account.setUsername(username);
        account.setPassword(password);
    }

    /**
     * Test for null or empty case
     */
    @Test(timeout = TIMEOUT)
    public void loginNullTest() {
        String user = "";
        String pass = null;
        assertFalse(Models.login(user, pass));
    }


    /**
     * Test for login
     */
    @Test(timeout = TIMEOUT)
    public void loginGoodTest() {
        Models.register(account);
        assertTrue(Models.login(account.getUsername(), account.getPassword()));
    }
}
