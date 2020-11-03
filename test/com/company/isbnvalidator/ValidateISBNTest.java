package com.company.isbnvalidator;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidateISBNTest {

    @Test
    public void checkAnValid10DigitISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("0140449116");
        assertTrue("first assert", result);

        result = validator.checkISBN("0140177396");
        assertTrue("second assert", result);
    }

    @Test
    public void checkAValid13DigitISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("9781853260087");
        assertTrue("first result", result);

        result = validator.checkISBN("9781853267338");
        assertTrue("second result", result);
    }

    @Test
    public void checkAnInvalid13DigitISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("9781853260088");
        assertFalse(result);
    }

    @Test
    public void checkAnInvalid10DigitISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("0140449117");
        assertFalse(result);
    }

    @Test (expected = NumberFormatException.class)
    public void nineDigitISNBsAreNotAllowed() {
        ValidateISBN validator = new ValidateISBN();
        validator.checkISBN("123456789");
        fail();
    }

    @Test (expected = NumberFormatException.class)
    public void nonNumeric10DigitISBNsAreNotAllowed() {
        ValidateISBN validator = new ValidateISBN();
        validator.checkISBN("helloworld");
        fail();
    }

    @Test
    public void TenDigitISBNNumbersEndingWithAnX() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("012000030X");
        assertTrue(result);
    }

    @Test
    public void toDelete() {
        ISBNServiceBookFetcher service = new ISBNServiceBookFetcher() {
            @Override
            public Book getBookInformation(String isbn) {
                return new Book("Bobby Brown", "3294829034", "Jimmy Bobby");
            }
        };


    }


}