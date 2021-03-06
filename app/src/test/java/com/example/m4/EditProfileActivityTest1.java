package com.example.m4;

import com.example.m4.controllers.EditProfileActivity;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EditProfileActivityTest1 {

    /**
     * Test for valid email address entry
     * @throws Exception throw exception when not valid
     */
    @Test
    public void emailValidationTest() throws Exception{
        assertTrue(EditProfileActivity.isValidEmail("yuchen111@gmail.com"));
        assertTrue(EditProfileActivity.isValidEmail("Siwei222@gmail.com"));
        assertTrue(EditProfileActivity.isValidEmail("Theresa333@gmail.com"));
        assertTrue(EditProfileActivity.isValidEmail("Yuxin444@gmail.com"));
        assertTrue(EditProfileActivity.isValidEmail("nick555@gmail.com"));
    }

    /**
     * Test for exception
     * @throws Exception throw exception when not valid
     */
    @Test (expected = IllegalArgumentException.class)
    public void emailExceptionTest() throws Exception {
        EditProfileActivity.isValidEmail("");
        EditProfileActivity.isValidEmail(null);
    }

    /**
     * Test for invalid email address entry
     * @throws Exception throw exception when not valid
     */
    @Test
    public void emailInvalidation() throws Exception {
        assertFalse(EditProfileActivity.isValidEmail("cs2340"));
        assertFalse(EditProfileActivity.isValidEmail("123@123..com"));
        assertFalse(EditProfileActivity.isValidEmail("あいうえお@example.com"));
        assertFalse(EditProfileActivity.isValidEmail("yuchen@gmail.com (Yuchen Cao)"));
    }
}
