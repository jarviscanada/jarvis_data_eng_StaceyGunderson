package ca.jrvs.apps.stockquote.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PositionService_IntTest {

    Quote.PositionService posService;
    QuoteHttpHelper http;
    Connection conn;
    QuoteDao quotedao;
    PositionDao positiondao;
    private static final String url = "jdbc:postgresql://localhost:5432/stockquote";
    private static final String user = "postgres";
    private static final String password = "password";


    @BeforeEach
    void setup(){
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url,"postgres","password");
            positiondao = new PositionDao(conn);
            http = new QuoteHttpHelper();
            posService = new Quote.PositionService(http, positiondao);
            quotedao = new QuoteDao(conn);

            quotedao.deleteAll();

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Quote quote = new Quote(0, "AMZN", 2.3,3,13,2,2, Date.valueOf("2023-10-13"),2,2,"2.3", timestamp);

            quotedao.save(quote);
            Quote quote2 = new Quote(0, "MSFT", 3,3,13,2,2, Date.valueOf("2023-10-13"),2,2,"2.3", timestamp);

            quotedao.save(quote2);


        }
        catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    @Test
    void listPositions() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Quote quote = new Quote(2, "AMZN", 2.3,3,13,2,2, Date.valueOf("2023-10-13"),2,2,"2.3", timestamp);
        Quote quote2 = new Quote(2, "MSFT", 2.3,3,13,2,2, Date.valueOf("2023-10-13"),2,2,"2.3", timestamp);

        Position expected2 = new Position(3,"MSFT", 3, 10);
        Position expected3 = new Position(3,"AMZN", 3, 11);

        posService.buy(quote, 3, 10);
        posService.buy(quote2, 3 ,11);

        List<Position> expected = new ArrayList();
        expected.add(expected3);
        expected.add(expected2);


        Iterable actual = posService.listPositions();
        assertEquals(expected, actual);
    }

    @Test
    void buy() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Quote quote = new Quote(2, "AMZN", 2.3,3,13,2,2, Date.valueOf("2023-10-13"),2,2,"2.3", timestamp);
        Position expected = new Position(0,"AMZN", 46, 460.00);

        Position preactual = posService.buy(quote, 23, 10);
        Position actual = posService.buy(quote, 23, 10);

        assertEquals(expected, actual);
    }

    @Test
    void sell() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Quote quote = new Quote(2, "AMZN", 2.3,3,13,2,2, Date.valueOf("2023-10-13"),2,2,"2.3", timestamp);
        Position set = new Position(0,"AMZN", 46, 460.00);
        Position buy = posService.buy(quote, 23, 10);
        posService.sell(72);

        Optional<Position> actual = Optional.empty();
        Optional<Position> expected = positiondao.findBySymbol("AMZN");

        assertEquals(expected, actual);
    }
}