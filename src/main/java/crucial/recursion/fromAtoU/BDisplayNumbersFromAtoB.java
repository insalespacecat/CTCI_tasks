package main.java.crucial.recursion.fromAtoU;

public class BDisplayNumbersFromAtoB {
    //Даны два целых числа A и В (каждое в отдельной строке).
    //Выведите все числа от A до B включительно, в порядке возрастания, если A < B, или в порядке убывания в противном случае.
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
