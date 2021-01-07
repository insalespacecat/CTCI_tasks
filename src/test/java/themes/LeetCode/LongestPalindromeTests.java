package test.java.themes.LeetCode;

import main.java.themes.ArraysAndStrings.LeetCode.LongestPalindrome;
import main.java.themes.ArraysAndStrings.LeetCode.LongestUnique;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LongestPalindromeTests {
    private final LongestPalindrome classToTest = new LongestPalindrome();

    @Test
    void AlgorithmTests(){
        assertEquals("aba", classToTest.longestPalindrome("caba"));
    }
}
