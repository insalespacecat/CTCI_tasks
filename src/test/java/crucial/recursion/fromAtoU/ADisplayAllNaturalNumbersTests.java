package test.java.crucial.recursion.fromAtoU;

import main.java.crucial.recursion.fromAtoU.ADisplayAllNaturalNumbers;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ADisplayAllNaturalNumbersTests {
    ADisplayAllNaturalNumbers classToTest = new ADisplayAllNaturalNumbers();

    @Test
    public void TestAlgorithm(){
        //We may wanna spy here, but we don't wan't to invoke libraries
        //like mockito just for this single test i guess?
        classToTest.naturalNumbers(1, 10);
    }
}
