package ca.jrvs.apps.practice.codingChallenges;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

public class SimpleCalculatorTest {

    SimpleCalculator calculator;

    @BeforeEach
    void init() {
        calculator = new SimpleCalculatorImpl();
    }

    @Before
    public void setUp() throws Exception {
        calculator = new SimpleCalculatorImpl();
    }

    @Test
    public void add() {
        int expected = 2;
        int actual = calculator.add(1, 1);
        assertEquals(expected, actual);
    }

    @Test
    public void subtract() {
        int expected = 0;
        int actual = calculator.subtract(1, 1);
        assertEquals(expected, actual);
    }

    @Test
    public void multiply() {
        int expected = 1;
        int actual = calculator.multiply(1, 1);
        assertEquals(expected, actual);
    }

    @Test
    public void divide() {
        double expected = 1;
        double actual = calculator.divide(1, 1);
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void power() {
        int expected = 1;
        int actual = calculator.power(1, 2);
        assertEquals(expected, actual);
    }

    @Test
    public void abs() {
        double expected = 1;
        double actual = calculator.abs(-1);
        assertEquals(expected, actual, 0.01);
    }
}