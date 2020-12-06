package test.java.themes.LeetCode;

import main.java.themes.ArraysAndStrings.LeetCode.WaterContainer;
import main.java.themes.ArraysAndStrings.LeetCode.WordSearch;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WaterContainerTests {
    private final WaterContainer classToTest = new WaterContainer();

    @Test
    public void optimizedTest(){
        int[] arr = {1,8,6,2,5,4,8,3,7};
        assertEquals(classToTest.maxArea(arr), 49);
    }
}
