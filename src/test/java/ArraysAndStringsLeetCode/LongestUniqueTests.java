package test.java.ArraysAndStringsLeetCode;

import main.java.ArraysAndStringsLeetCode.LongestUnique;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LongestUniqueTests {
    private final LongestUnique classToTest = new LongestUnique();

    @Test
    void AlgorithmTests(){
        assertEquals(3, classToTest.longestUnique("abcabcbb"));
        assertEquals(1, classToTest.longestUnique("bbbbbbbb"));
        assertEquals(3, classToTest.longestUnique("pwwkew"));
        assertEquals(2, classToTest.longestUnique("cdd"));
    }
}
