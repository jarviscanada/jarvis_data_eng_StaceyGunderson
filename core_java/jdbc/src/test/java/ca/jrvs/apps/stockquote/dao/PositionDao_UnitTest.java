package ca.jrvs.apps.stockquote.dao;

import org.checkerframework.common.returnsreceiver.qual.This;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;


class PositionDao_UnitTest {

    PositionDao positionDao;
    @Mock
    Connection conn;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        positionDao = new PositionDao(conn);
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