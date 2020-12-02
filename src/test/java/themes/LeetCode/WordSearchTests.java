package test.java.themes.LeetCode;

import main.java.themes.ArraysAndStrings.LeetCode.WordSearch;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WordSearchTests {
    private final WordSearch classToTest = new WordSearch();

    @Test
    void BruteForceAlgorithmTests(){
        /*
        char[][] arr = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
       // assertTrue(classToTest.bruteForce(arr, "ABCCED"));
        char[][] arr2 = {{'a'}};
        assertFalse(classToTest.bruteForce(arr2, "b"));
        //assertEquals("ACB", classToTest.convertBruteForce("ABC", 2));
         */
    }
    @Test
    void OptimizedAlgorithmTests() {
        char[][] arr9 = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'E', 'S'}, {'A', 'D', 'E', 'E'}};
        assertTrue(classToTest.optimized(arr9, "ABCESEEEFS"));
        char[][] arr = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        assertTrue(classToTest.optimized(arr, "ABCCED"));
        char[][] arr2 = {{'a'}};
        assertFalse(classToTest.optimized(arr2, "b"));
        char[][] arr3 = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        assertFalse(classToTest.optimized(arr3, "ABAB"));
        char[][] arr4 = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        assertFalse(classToTest.optimized(arr4, "AAAAAAAA"));
        char[][] arr5 = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        assertTrue(classToTest.optimized(arr5, "ABCE"));
        char[][] arr6 = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        assertTrue(classToTest.optimized(arr6, "ABCESE"));
        char[][] arr7 = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        assertTrue(classToTest.optimized(arr7, "ASADE"));
        char[][] arr8 = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        assertFalse(classToTest.optimized(arr8, "ABCB"));
    }
}
