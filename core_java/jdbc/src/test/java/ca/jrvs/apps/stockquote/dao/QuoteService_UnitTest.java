package ca.jrvs.apps.stockquote.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.net.http.HttpHeaders;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class QuoteService_UnitTest {

    QuoteService quoteService;
    @Mock
    QuoteDao mockquotedao;
    @Mock
    Connection mockconn;
    @Mock
    QuoteHttpHelper mockhttp;
    @Mock
    QuoteDao mockdao;

    @BeforeEach
    void setUp() {

        mockconn = mock(Connection.class);
        mockhttp = mock(QuoteHttpHelper.class);
        mockquotedao = mock(QuoteDao.class);
        quoteService = new QuoteService(mockhttp, mockquotedao);

    }

    @Test
    void fetchQuoteDataFromAPI() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Quote expected = new Quote(2, "TEST", 1,1,1,2,2, Date.valueOf("2023-10-13"),2,2,"2.3", timestamp);

        when(mockhttp.fetchQuoteInfo("TEST")).thenReturn(expected);
        Optional<Quote> actual= quoteService.fetchQuoteDataFromAPI("TEST");

        assertEquals(expected, actual.get());
    }

    @Test
    void storeQuoteData() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Quote expected = new Quote(2, "TEST", 1,1,1,2,2, Date.valueOf("2023-10-13"),2,2,"2.3", timestamp);

        Optional<Quote> actual= quoteService.storeQuoteData(expected);

        assertEquals(expected, actual.get());
    }
}