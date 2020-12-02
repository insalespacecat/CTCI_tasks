package main.java.crucial.recursion.fromAtoU;

public class ADisplayAllNaturalNumbers {
    //Display all natural numbers from 1 to N
    //What is the end condition?
    // - i == N
    //What is the step of recursion?
    //System.out.println(i)
    //naturalNumbers(i+1);
    //We will fill the array to simplify unit test

    public void naturalNumbers(int i, int N){
        if(i == N){
           return;
        }
        System.out.println(i);
        naturalNumbers(i+1, N);
    }

    public static void main(String[] args){
    }
}
