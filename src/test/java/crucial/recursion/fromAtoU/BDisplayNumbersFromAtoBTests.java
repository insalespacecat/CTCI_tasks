package test.java.crucial.recursion.fromAtoU;

import main.java.crucial.recursion.fromAtoU.ADisplayAllNaturalNumbers;
import main.java.crucial.recursion.fromAtoU.BDisplayNumbersFromAtoB;
import org.junit.jupiter.api.Test;

public class BDisplayNumbersFromAtoBTests {
    BDisplayNumbersFromAtoB classToTest = new BDisplayNumbersFromAtoB();

    @Test
    public void TestAlgorithm(){
        //We may wanna spy here, but we don't wan't to invoke libraries
        //like mockito just for this single test i guess?
        System.out.println(classToTest.recursion("10", "20"));
        System.out.println(classToTest.recursion("20", "10"));
    }
}
