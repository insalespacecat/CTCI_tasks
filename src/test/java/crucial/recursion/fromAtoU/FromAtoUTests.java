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

        System.out.println(classToTest.digitsRtl(1447));

        System.out.println(classToTest.digitsLtr(1447));

        System.out.println(classToTest.checkSimplicity(7, 1));
        System.out.println(classToTest.checkSimplicity(17, 1));
        System.out.println(classToTest.checkSimplicity(101, 1));
        System.out.println(classToTest.checkSimplicity(80, 1));
        System.out.println(classToTest.checkSimplicity(15, 1));
        System.out.println(classToTest.checkSimplicity(21, 1));

        classToTest.printSimpleDividers(8, 2);

        System.out.println(classToTest.isAPalindrome("pallap", 0, 5));
        System.out.println(classToTest.isAPalindrome("no", 0, 1));
        System.out.println(classToTest.isAPalindrome("hei amigos", 0, 1));
    }
}
