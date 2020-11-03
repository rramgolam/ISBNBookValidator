package com.company.isbnvalidator;

public class StockManager {

    private ISBNServiceBookFetcher webService;
    private ISBNServiceBookFetcher databaseService;


    public void setDatabaseService(ISBNServiceBookFetcher databaseService) {
        this.databaseService = databaseService;
    }

    public void setWebService(ISBNServiceBookFetcher webService) {
        this.webService = webService;
    }

    public String getLocatorCode(String isbn) {

        Book book = databaseService.getBookInformation(isbn);
        if (book == null) book = webService.getBookInformation(isbn);

        StringBuilder locatorCode = new StringBuilder();
        locatorCode.append(isbn.substring(isbn.length() - 4));
        locatorCode.append(book.getAuthor().charAt(0));
        locatorCode.append(book.getTitle().split(" ").length);

        return locatorCode.toString();
    }
}
