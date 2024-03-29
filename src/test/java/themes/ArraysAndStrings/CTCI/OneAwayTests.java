package test.java.themes.ArraysAndStrings.CTCI;

import main.java.themes.ArraysAndStrings.CTCI.OneAway;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OneAwayTests {
    private final OneAway classToTest = new OneAway();

    @Test
    void AlgorithmTests(){
        assertTrue(classToTest.oneAway("pale", "ple"));
        assertTrue(classToTest.oneAway("pale", "pales"));
        assertTrue(classToTest.oneAway("pale", "bale"));
        assertFalse(classToTest.oneAway("pale", "bake"));
    }


}
