package com.company.isbnvalidator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValidateISBNTest {

    private ValidateISBN validator;

    @Before
    public void setup() {
        validator = new ValidateISBN();
    }

    @Test
    public void checkAnValid10DigitISBN() {
        boolean result = validator.checkISBN("0140449116");
        assertTrue("first assert", result);

        result = validator.checkISBN("0140177396");
        assertTrue("second assert", result);
    }

    @Test
    public void checkAValid13DigitISBN() {
        boolean result = validator.checkISBN("9781853260087");
        assertTrue("first result", result);

        result = validator.checkISBN("9781853267338");
        assertTrue("second result", result);
    }

    @Test
    public void checkAnInvalid13DigitISBN() {
        boolean result = validator.checkISBN("9781853260088");
        assertFalse(result);
    }

    @Test
    public void checkAnInvalid10DigitISBN() {
        boolean result = validator.checkISBN("0140449117");
        assertFalse(result);
    }

    @Test (expected = NumberFormatException.class)
    public void nineDigitISNBsAreNotAllowed() {
        validator.checkISBN("123456789");
        fail();
    }

    @Test (expected = NumberFormatException.class)
    public void nonNumeric10DigitISBNsAreNotAllowed() {
        validator.checkISBN("helloworld");
        fail();
    }

    @Test
    public void TenDigitISBNNumbersEndingWithAnX() {
        boolean result = validator.checkISBN("012000030X");
        assertTrue(result);
    }

}