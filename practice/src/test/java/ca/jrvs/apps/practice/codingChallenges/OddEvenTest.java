package ca.jrvs.apps.practice.codingChallenges;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OddEvenTest {
    OddEven oddeven;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void oddEvenMod() {
        String expected = "True";
        String actual = oddeven.oddEvenMod(1);
        assertEquals(expected, actual);
    }

    @Test
    public void oddEvenBit() {
        String expected = null;
        String actual = oddeven.oddEvenBit(1);
        assertEquals(expected, actual);
    }
}