package test.java.ArraysAndStringsLeetCode;

import main.java.ArraysAndStringsLeetCode.LongestUnique;
import main.java.ArraysAndStringsLeetCode.ZigZagConversion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ZigZagConversionTests {
    private final ZigZagConversion classToTest = new ZigZagConversion();

    @Test
    void AlgorithmTests(){
        assertEquals("PAHNAPLSIIGYIR", classToTest.convert("PAYPALISHIRING", 3));
        assertEquals("AB", classToTest.convert("AB", 1));
        assertEquals("ACB", classToTest.convert("ABC", 2));
    }
}
