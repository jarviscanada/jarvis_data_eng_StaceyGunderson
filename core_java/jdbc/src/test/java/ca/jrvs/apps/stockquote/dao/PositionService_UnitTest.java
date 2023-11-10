package ca.jrvs.apps.stockquote.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PositionService_UnitTest {

    Quote.PositionService posService;

    @Mock
    QuoteHttpHelper mockhttp;
    @Mock
    Connection mockconn;
    @Mock
    PositionDao mockpositiondao;

    @BeforeEach
    void setup(){

        mockconn = mock(Connection.class);
        mockhttp = mock(QuoteHttpHelper.class);
        mockpositiondao = mock(PositionDao.class);
        posService = new Quote.PositionService(mockhttp, mockpositiondao);

    }

    @Test
    void listPositions() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Position expected2 = new Position(3,"MSFT", 23, 4.2);
        Position expected3 = new Position(3,"MSAS", 23, 4.2);

        List<Position> expected = new ArrayList();
        expected.add(expected3);
        expected.add(expected2);

        when(mockpositiondao.findAll()).thenReturn(expected);

        Iterable actual = posService.listPositions();
        assertEquals(expected, actual);
    }

    @Test
    void buy() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Quote quote = new Quote(2, "MSFT", 2.3,3,13,2,2, Date.valueOf("2023-10-13"),2,2,"2.3", timestamp);
        Position expected = new Position(0,"MSFT", 24, 240.00);
        Position old = new Position(0,"MSFT", 1, 10.00);

        when(mockpositiondao.findBySymbol("MSFT")).thenReturn(Optional.of(old));
        Position actual = posService.buy(quote, 23, 10);

        assertEquals(expected, actual);
    }

    @Test
    void sell() {
        posService.sell(3);
        verify(mockpositiondao).deleteById(3);
    }
}