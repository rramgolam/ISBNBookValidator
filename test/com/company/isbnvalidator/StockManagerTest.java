package com.company.isbnvalidator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class StockManagerTest {

    private ISBNServiceBookFetcher webService;
    private ISBNServiceBookFetcher databaseService;
    private StockManager manager;

    @Before
    public void setup() {
        webService = mock(ISBNServiceBookFetcher.class);
        databaseService = mock(ISBNServiceBookFetcher.class);

        manager = new StockManager();
        manager.setWebService(webService);
        manager.setDatabaseService(databaseService);
    }

    @Test
    public void canGetACorrectLocatorCode() {
//        ** Replaced by mocks - example before and after **
//        ISBNServiceBookFetcher webService = new ISBNServiceBookFetcher() {
//            @Override
//            public Book getBookInformation(String isbn) {
//                return new Book("Of Mice And Men","0140177396","John Steinbeck");
//            }
//        };
//
//        ISBNServiceBookFetcher databaseService = new ISBNServiceBookFetcher() {
//            @Override
//            public Book getBookInformation(String isbn) {
//                return null;
//            }
//        };


        when(webService.getBookInformation(anyString()))
                .thenReturn(new Book("Of Mice And Men","0140177396","John Steinbeck"));
        when(databaseService.getBookInformation(anyString()))
                .thenReturn(null);

        String isbn = "0140177396";
        String result = manager.getLocatorCode(isbn);

        assertEquals("7396J4", result);
    }

    @Test
    public void databaseIsUsedIfWebServiceDataIsPresent() {

        when(databaseService.getBookInformation("0140177396")).thenReturn(new Book("abc","0140177396","abc"));

        String isbn = "0140177396";
        String result = manager.getLocatorCode(isbn);

        verify(databaseService, times(1)).getBookInformation("0140177396");
        verify(webService, times(0)).getBookInformation(anyString());

    }

    @Test
    public void webserviceIsUsedIfDataIsNotPresentInDatabase() {

        when(webService.getBookInformation("0140177396")).thenReturn(new Book("abc","0140177396","abc"));
        when(databaseService.getBookInformation("0140177396")).thenReturn(null);

        String isbn = "0140177396";
        String result = manager.getLocatorCode(isbn);

        verify(databaseService, times(1)).getBookInformation("0140177396");
        verify(webService, times(1)).getBookInformation("0140177396");
    }

    @After
    public void teardown() {
        System.out.println("<Placeholder> Teardown after each test.");
    }
}
