package main.java.crucial.recursion.fromAtoU;

public class BDisplayNumbersFromAtoB {
    public String recursion(String A, String B) {
        if (Integer.parseInt(A) == Integer.parseInt(B)) {
            return A;
        }
        if (Integer.parseInt(A) > Integer.parseInt(B)) {
            return recursion(A, String.valueOf((Integer.parseInt(B) + 1))) + " " + B;
        }
        if (Integer.parseInt(A) < Integer.parseInt(B)) {
            return recursion(A, String.valueOf((Integer.parseInt(B) - 1))) + " " + B;
        } else {
            return "Error";
        }
    }
}
