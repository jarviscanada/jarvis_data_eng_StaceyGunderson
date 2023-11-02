package ca.jrvs.apps.stockquote.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.*;

class PositionDao_IntTest {

    private static final String url = "jdbc:postgresql://localhost:5432/stockquote";
    private static final String user = "postgres";
    private static final String password = "password";

    PositionDao positionDao;
    Connection conn;

    @BeforeEach
    void setUp() {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            positionDao = new PositionDao(conn);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void save() {
        Position pos = new Position(3,"MSFT", 23, 4.2);

        positionDao.save(pos);


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