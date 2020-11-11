package test.java.ArraysAndStringsLeetCode;

import main.java.ArraysAndStringsLeetCode.WordSearch;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WordSearchTests {
    private final WordSearch classToTest = new WordSearch();

    @Test
    void BruteForceAlgorithmTests(){
        char[][] arr = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
       // assertTrue(classToTest.bruteForce(arr, "ABCCED"));
        char[][] arr2 = {{'a'}};
        assertFalse(classToTest.bruteForce(arr2, "b"));
        //assertEquals("ACB", classToTest.convertBruteForce("ABC", 2));
    }

    @Test
    void OptimizedAlgorithmTests() {

    }
}
