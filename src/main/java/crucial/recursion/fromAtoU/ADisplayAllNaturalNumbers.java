package main.java.crucial.recursion.fromAtoU;

public class ADisplayAllNaturalNumbers {
    //Display all natural numbers from 1 to N
    //What is the end condition?
    // - i == N
    //What is the step of recursion?
    //System.out.println(i)
    //naturalNumbers(i+1);
    //We will fill the array to simplify unit test
    //OK, that is incorrect, because we have modified the task
    //and said that we have i provided when we have not
    //The correct way to solve this will be to use recursion properties:
    public String naturalNumbers(int N){
        if(N == 1) {
            return "1";
        }
        return naturalNumbers(N-1) + " " + N;
    }

    public static void main(String[] args){
    }
}
