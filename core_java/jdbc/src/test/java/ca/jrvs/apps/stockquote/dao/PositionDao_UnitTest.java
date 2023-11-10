package ca.jrvs.apps.stockquote.dao;


import java.sql.Connection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PositionDao_UnitTest {

    PositionDao dao;
    QuoteDao quotedao;
    Connection conn;
    private static final String url = "jdbc:postgresql://localhost:5432/stockquote";

    @BeforeEach
    void setup(){
                try {
            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(url,"postgres","password");
            dao = new PositionDao(conn);
            quotedao = new QuoteDao(conn);
        }
        catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    //
    @Test
    void update() {
        dao.deleteAll();
        quotedao.deleteAll();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Quote quote = new Quote(2, "TEST", 2.3,3,13,2,2, Date.valueOf("2023-10-13"),2,2,"2.3", timestamp);
        quotedao.save(quote);
        Position pos = new Position(30,"TEST", 23, 4.2);
        Position expected = new Position(30,"TEST", 26, 6);

        dao.save(pos);
        dao.update(expected);

        Optional<Position> actual = dao.findBySymbol("TEST");
        assertEquals(expected, actual.get());
    }

    @Test
    void save() {
        dao.deleteAll();
        quotedao.deleteAll();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Quote quote = new Quote(2, "TEST", 2.3,3,13,2,2, Date.valueOf("2023-10-13"),2,2,"2.3", timestamp);
        quotedao.save(quote);
        Position expected = new Position(30,"TEST", 23, 4.2);

        dao.save(expected);

        Optional<Position> actual = dao.findBySymbol("TEST");
        assertEquals(expected, actual.get());
    }

    @Test
    void findById() {
        dao.deleteAll();
        quotedao.deleteAll();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Quote quote = new Quote(2, "TEST", 2.3,3,13,2,2, Date.valueOf("2023-10-13"),2,2,"2.3", timestamp);
        quotedao.save(quote);
        Position expected = new Position(30,"TEST", 23, 4.2);

        dao.save(expected);

        Optional<Position> actual = dao.findById(30);
        assertEquals(expected, actual.get());
    }

    @Test
    void findBySymbol() {
        dao.deleteAll();
        quotedao.deleteAll();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Quote quote = new Quote(2, "TEST", 2.3,3,13,2,2, Date.valueOf("2023-10-13"),2,2,"2.3", timestamp);
        quotedao.save(quote);
        Position expected = new Position(30,"TEST", 23, 4.2);

        dao.save(expected);

        Optional<Position> actual = dao.findBySymbol("TEST");
        assertEquals(expected, actual.get());
    }

    @Test
    void findAll() {
        dao.deleteAll();
        quotedao.deleteAll();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Quote quote = new Quote(2, "TEST", 2.3,3,13,2,2, Date.valueOf("2023-10-13"),2,2,"2.3", timestamp);
        quotedao.save(quote);
        Position expected3 = new Position(30,"TEST", 23, 4.2);
        Position expected2 = new Position(30,"TEST", 23, 4.2);

        List<Position> expected = new ArrayList();
        expected.add(expected3);
        expected.add(expected2);

        dao.save(expected3);
        dao.save(expected2);

        Iterable<Position> actual = dao.findAll();
        assertEquals(expected, actual);
    }

    @Test
    void deleteById() {
        dao.deleteAll();
        quotedao.deleteAll();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Quote quote = new Quote(2, "TEST", 2.3,3,13,2,2, Date.valueOf("2023-10-13"),2,2,"2.3", timestamp);
        quotedao.save(quote);
        Position expected3 = new Position(30,"TEST", 23, 4.2);

        dao.deleteById(30);

        Optional<Position> actual = dao.findById(30);
        Optional<Object> expected = Optional.empty();

        assertEquals(expected, actual);
    }

    @Test
    void deleteAll() {
        dao.deleteAll();
        quotedao.deleteAll();

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Quote quote = new Quote(2, "TEST", 2.3,3,13,2,2, Date.valueOf("2023-10-13"),2,2,"2.3", timestamp);
        quotedao.save(quote);
        Position expected3 = new Position(30,"TEST", 23, 4.2);
        Position expected2 = new Position(30,"TEST", 23, 4.2);
        dao.save(expected3);
        dao.save(expected2);

        dao.deleteAll();
        Iterable<Position> actual = dao.findAll();

        ArrayList expected = new ArrayList();

        assertEquals(expected, actual);
    }
}