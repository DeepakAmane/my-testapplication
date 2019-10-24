package com.example.testapp;

import androidx.databinding.ObservableField;

import com.example.testapp.utils.Utils;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

public class LoginValidatorTest {

    @Test
    public void emailValidator_CorrectEmailSimple_ReturnsTrue() {
        assertTrue(Utils.isEmailValid("deepaksubamane@email.com"));
    }

    @Test
    public void emailValidator_CorrectEmailSubDomain_ReturnsTrue() {
        assertTrue(Utils.isEmailValid("deepaksubamane@email.co.uk"));
    }

    @Test
    public void emailValidator_InvalidEmailNoTld_ReturnsFalse() {
        assertFalse(Utils.isEmailValid("deepaksubamane@email"));
    }

    @Test
    public void emailValidator_InvalidEmailDoubleDot_ReturnsFalse() {
        assertFalse(Utils.isEmailValid("deepaksubamane@email..com"));
    }

    @Test
    public void emailValidator_InvalidEmailNoUsername_ReturnsFalse() {
        assertFalse(Utils.isEmailValid("@email.com"));
    }

    @Test
    public void emailValidator_EmptyString_ReturnsFalse() {
        assertFalse(Utils.isEmailValid(""));
    }

    @Test
    public void emailValidator_check_Email_isNull() {
        ObservableField<String> emailNull = new ObservableField<>();
        assertNull("Email is Null", emailNull.get());
    }

    @Test
    public void passwordValidator_check_Password_isNull() {
        ObservableField<String> passwordNull = new ObservableField<>();
        assertNull("Password is Null", passwordNull.get());
    }


    @Test
    public void passwordValidator_CorrectPassword_ReturnsTrue() {
        assertTrue(Utils.isValidPassword("John@1"));
        assertTrue(Utils.isValidPassword("JOHN@1"));
        assertTrue(Utils.isValidPassword("johN1@"));
        assertTrue(Utils.isValidPassword("J@1a"));
        assertFalse(Utils.isValidPassword("john"));
        assertFalse(Utils.isValidPassword("john@"));
        assertFalse(Utils.isValidPassword("john@1"));
        assertFalse(Utils.isValidPassword("john1@"));
        assertFalse(Utils.isValidPassword("joh1@"));
        assertFalse(Utils.isValidPassword("johN@"));
    }
}
