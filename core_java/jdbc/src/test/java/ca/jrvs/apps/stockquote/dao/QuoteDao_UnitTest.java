package ca.jrvs.apps.stockquote.dao;

import java.sql.Connection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QuoteDao_UnitTest {

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