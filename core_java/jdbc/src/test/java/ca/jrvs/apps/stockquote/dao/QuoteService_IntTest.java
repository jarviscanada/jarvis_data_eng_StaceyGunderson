package ca.jrvs.apps.stockquote.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class QuoteService_IntTest {
    private static final String user = "postgres";
    private static final String password = "password";
    QuoteDao quotedao;
    Connection conn;
    QuoteHttpHelper http;
    QuoteService quoteService;
    private static final String url = "jdbc:postgresql://localhost:5432/stockquote";



    @BeforeEach
    void setUp() {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url,"postgres","password");
            quotedao = new QuoteDao(conn);
            http = new QuoteHttpHelper();
            quoteService = new QuoteService(http, quotedao);
        }
        catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    @Test
    void fetchQuoteDataFromAPI() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Quote expected = new Quote(0, "AMZN", 2.3,3,13,2,2, Date.valueOf("2023-10-13"),2,2,"2.3", timestamp);

        Optional<Quote> actual= quoteService.fetchQuoteDataFromAPI("AMZN");
        assertEquals(expected, actual.get());
    }

    @Test
    void storeQuoteData() {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Quote expected = new Quote(1, "TEST", 1,1,1,2,2, Date.valueOf("2023-10-13"),2,2,"2.3", timestamp);

        Optional<Quote> actual= quoteService.storeQuoteData(expected);

        assertEquals(expected, actual.get());
    }
}