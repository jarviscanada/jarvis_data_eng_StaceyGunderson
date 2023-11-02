package ca.jrvs.apps.stockquote.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class QuoteDao_IntTest {

    QuoteDao quoteDao;
    Connection conn;

    @BeforeEach
    void setUp() {
        quoteDao = new QuoteDao(conn);

    }

    @Test
    void save() {
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void deleteAll() {
    }
}