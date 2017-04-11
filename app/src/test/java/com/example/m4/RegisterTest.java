package com.example.m4;

import com.example.m4.models.AccountType;
import com.example.m4.models.Models;
import com.example.m4.models.User;
import com.example.m4.models.Worker;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by nickstoltz on 4/11/17.
 *
 * Tests the edge cases for registering a new user in the back end.
 * Including username and password size control, null inputs, and empty strings.
 */


public class RegisterTest {
    private String validPassword;
    private String validUsername;
    private AccountType accountType;


    /**
     *
     */
    @Before
    public void initialize() {
        validPassword = "aaaa8888";
        validUsername = "helo";
        accountType = new Worker();
        accountType.setUsername(validUsername);
        accountType.setPassword(validPassword);
    }

    @Test
    public void control() {
        assertTrue(Models.register(accountType));
    }

    /**
     * Test for size of username
     */
    @Test
    public void testNullUserName() {
        String username = null;
        accountType.setUsername(username);
        assertFalse(Models.register(accountType));
    }

    @Test
    public void testEmptyStringUserName() {
        String username = "";
        accountType.setUsername(username);
        assertFalse(Models.register(accountType));
    }

    @Test
    public void testBigUserName() {
        StringBuffer s = new StringBuffer();
        for (int i = 0; i < 1000; i++) {
            s.append(" ");
        }
        String username = s.toString();
        accountType.setUsername(username);
        assertFalse(Models.register(accountType));
    }

    @Test
    public void testSmallUserName() {
        String username = " ";
        AccountType accountType = new User();
        accountType.setUsername(username);
        assertFalse(Models.register(accountType));
    }

    @Test
    public void testNullPassword() {
        String password = " ";
        AccountType accountType = new User();
        accountType.setPassword(password);
        assertFalse(Models.register(accountType));
    }


    @Test
    public void testEmptyStringPassword() {
        String password = "";
        accountType.setPassword(password);
        assertFalse(Models.register(accountType));
    }

    @Test
    public void testBigPassword() {
        StringBuffer s = new StringBuffer();
        for (int i = 0; i < 1000; i++) {
            s.append(" ");
        }
        String password = s.toString();
        accountType.setPassword(password);
        assertFalse(Models.register(accountType));
    }

    @Test
    public void testSmallPassword() {
        String password = " ";
        accountType.setPassword(password);
        assertFalse(Models.register(accountType));
    }
}
