package test.java.themes.LeetCode;

import main.java.themes.ArraysAndStrings.LeetCode.ZigZagConversion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ZigZagConversionTests {
    private final ZigZagConversion classToTest = new ZigZagConversion();

    @Test
    void BruteForceAlgorithmTests(){
        assertEquals("PAHNAPLSIIGYIR", classToTest.convertBruteForce("PAYPALISHIRING", 3));
        assertEquals("AB", classToTest.convertBruteForce("AB", 1));
        assertEquals("ACB", classToTest.convertBruteForce("ABC", 2));
    }

    @Test
    void OptimizedAlgorithmTests(){
        assertEquals("PAHNAPLSIIGYIR", classToTest.convertOptimized("PAYPALISHIRING", 3));
        assertEquals("AB", classToTest.convertOptimized("AB", 1));
        assertEquals("ACB", classToTest.convertOptimized("ABC", 2));
        assertEquals("PINALSIGYAHRPI", classToTest.convertOptimized("PAYPALISHIRING", 4));
    }
}
