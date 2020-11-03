package com.company.isbnvalidator;

public class ValidateISBN {

    public static final int SHORT_ISBN_LENGTH = 10;
    public static final int LONG_ISBN_LENGTH = 13;
    public static final int SHORT_ISBN_MULTIPLIER = 11;
    public static final int LONG_ISBN_MULTIPLIER = 10;

    public boolean checkISBN(String isbn) {

        if (isbn.length() == 10) {
            return isShortISBNValid(isbn);
        } else if (isbn.length() == 13){
            return isLongISBNValid(isbn);
        }

        throw new NumberFormatException("ISBN numbers must be 10 digits long.");

    }

    private boolean isLongISBNValid(String isbn) {
        // calc for 13 digit
        int total = 0;
        int x = 1;
        for (int i = 0; i < LONG_ISBN_LENGTH; i++) {

            if (i > 0) {
               x = (x == 1) ? 3 : 1;
            }
            total += x * isbn.charAt(i);

        }
        return (total % LONG_ISBN_MULTIPLIER == 0);

    }

    private boolean isShortISBNValid(String isbn) {

        int total = 0;
        for (int i = 0; i < SHORT_ISBN_LENGTH; i++) {
            if (!Character.isDigit(isbn.charAt(i))) {
                if (i == 9 && isbn.charAt(i) == 'X') {
                    total += 10;
                } else {
                    throw new NumberFormatException("ISBN numbers must be all digits");
                }
            }
            total += Character.getNumericValue(isbn.charAt(i)) * (SHORT_ISBN_LENGTH - i);
        }

        return (total % SHORT_ISBN_MULTIPLIER == 0);
    }

}
