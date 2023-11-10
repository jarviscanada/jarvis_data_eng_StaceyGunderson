package ca.jrvs.apps.stockquote.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

class QuoteDao_UnitTest {


    QuoteDao quotedao;
    Connection conn;
    private static final String url = "jdbc:postgresql://localhost:5432/stockquote";

    @BeforeEach
    void setup(){
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url,"postgres","password");
            quotedao = new QuoteDao(conn);
        }
        catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    @Test
    void update() {
        quotedao.deleteAll();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Quote start = new Quote(2, "TEST", 1,1,1,2,2, Date.valueOf("2023-10-13"),2,2,"2.3", timestamp);
         Quote expected = new Quote(2, "TEST", 2.3,3,13,2,2, Date.valueOf("2023-10-13"),2,2,"2.3", timestamp);
        quotedao.save(start);
        quotedao.update(expected);

        Optional<Quote> actual= quotedao.findBySymbol("TEST");

        assertEquals(expected, actual.get());
    }

    @Test
    void save() {
        quotedao.deleteAll();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Quote expected = new Quote(2, "TEST", 2.3,3,13,2,2, Date.valueOf("2023-10-13"),2,2,"2.3", timestamp);
        quotedao.save(expected);

        Optional<Quote> actual= quotedao.findBySymbol("TEST");

        //verify(expected, actual.get());
    }

    @Test
    void findBySymbol() {
        quotedao.deleteAll();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Quote expected = new Quote(2, "TEST", 2.3,3,13,2,2, Date.valueOf("2023-10-13"),2,2,"2.3", timestamp);
        quotedao.save(expected);

        Optional<Quote> actual= quotedao.findBySymbol("TEST");

        assertEquals(expected, actual.get());
    }

    @Test
    void findById() {
        quotedao.deleteAll();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Quote expected = new Quote(2, "TEST", 2.3,3,13,2,2, Date.valueOf("2023-10-13"),2,2,"2.3", timestamp);
        quotedao.save(expected);

        Optional<Quote> actual= quotedao.findById(2);

        assertEquals(expected, actual.get());
    }

    @Test
    void findAll() {
        quotedao.deleteAll();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Quote expected3 = new Quote(2, "TEST", 2.3,3,13,2,2, Date.valueOf("2023-10-13"),2,2,"2.3", timestamp);
        Quote expected2 = new Quote(2, "TEST", 2.3,3,13,2,2, Date.valueOf("2023-10-13"),2,2,"2.3", timestamp);
        quotedao.save(expected3);
        quotedao.save(expected2);

        List<Quote> expected = new ArrayList();
        expected.add(expected3);
        expected.add(expected2);


        Iterable actual = quotedao.findAll();

        assertEquals(expected, actual);
    }

    @Test
    void deleteById() {
        quotedao.deleteAll();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Quote expected = new Quote(2, "TEST", 2.3,3,13,2,2, Date.valueOf("2023-10-13"),2,2,"2.3", timestamp);
        quotedao.save(expected);
        quotedao.deleteById(2);

        Optional<Quote> actual= Optional.empty();

        assertEquals(expected, actual.get());

    }

    @Test
    void deleteAll() {
        quotedao.deleteAll();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Quote expected3 = new Quote(2, "TEST", 2.3,3,13,2,2, Date.valueOf("2023-10-13"),2,2,"2.3", timestamp);
        Quote expected2 = new Quote(2, "TEST", 2.3,3,13,2,2, Date.valueOf("2023-10-13"),2,2,"2.3", timestamp);
        quotedao.save(expected3);
        quotedao.save(expected2);

        quotedao.deleteAll();
        Iterable<Position> actual = quotedao.findAll();
        ArrayList expected = new ArrayList();

        assertEquals(expected, actual);
    }
}