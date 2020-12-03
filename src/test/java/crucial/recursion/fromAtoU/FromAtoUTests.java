package test.java.crucial.recursion.fromAtoU;

import main.java.crucial.recursion.fromAtoU.FromAtoU;
import org.junit.jupiter.api.Test;

public class FromAtoUTests {
    FromAtoU classToTest = new FromAtoU();
    @Test
    public void TestAlgorithm(){
        System.out.println(classToTest.naturalNumbers(10));

        System.out.println(classToTest.numbersFromAToB("10", "20"));
        System.out.println(classToTest.numbersFromAToB("20", "10"));

        System.out.println(classToTest.AkkermanFunc(2, 1));

        System.out.println(classToTest.isPowOf2(15));

        System.out.println(classToTest.sumOfDigits(1447));
    }
}
