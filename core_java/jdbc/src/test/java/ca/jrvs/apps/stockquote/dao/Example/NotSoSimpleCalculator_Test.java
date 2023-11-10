package ca.jrvs.apps.stockquote.dao.Example;

import ca.jrvs.apps.stockquote.dao.Example.NotSoSimpleCalculator;
import ca.jrvs.apps.stockquote.dao.Example.NotSoSimpleCalculatorImpl;
import ca.jrvs.apps.stockquote.dao.Example.SimpleCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NotSoSimpleCalculator_Test {

    NotSoSimpleCalculator calc;

    @Mock
    SimpleCalculator mockSimpleCalc;

    @BeforeEach
    void init() {
        calc = new NotSoSimpleCalculatorImpl(mockSimpleCalc);
    }

    @Test
    void test_power() {
        //write your test here
    }

    @Test
    void test_abs() {
        //This test will currently fail
        //Consider if the provided logic in NotSoSimpleCalcualtorImpl is correct
        //Consider if you need to add anything to this test case (hint: you do)
        //Mockito.when(calc.abs(10));
        //doReturn(-10).when(mockSimpleCalc).multiply(10,-1);
        when(mockSimpleCalc.multiply(-10,-1)).thenReturn(10);
        int expected = 10;
        int actual = calc.abs(-10);
        assertEquals(expected, actual);
    }

    @Test
    void test_sqrt() {
        //write your test here
    }

}