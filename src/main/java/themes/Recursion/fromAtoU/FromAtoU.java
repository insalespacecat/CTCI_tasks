package main.java.themes.Recursion.fromAtoU;

public class FromAtoU {

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

    //Даны два целых числа A и В (каждое в отдельной строке).
    //Выведите все числа от A до B включительно, в порядке возрастания, если A < B, или в порядке убывания в противном случае
    public String numbersFromAToB(String A, String B) {
        if (Integer.parseInt(A) == Integer.parseInt(B)) {
            return A;
        }
        if (Integer.parseInt(A) > Integer.parseInt(B)) {
            return numbersFromAToB(A, String.valueOf((Integer.parseInt(B) + 1))) + " " + B;
        }
        if (Integer.parseInt(A) < Integer.parseInt(B)) {
            return numbersFromAToB(A, String.valueOf((Integer.parseInt(B) - 1))) + " " + B;
        } else {
            return "Error";
        }
    }

    //Implement Akkerman Function
    public int AkkermanFunc(int m, int n){
        if(m == 0){
            return n+1;
        }
        if(m > 0 && n == 0){
            return AkkermanFunc(m-1, 1);
        }
        if(m > 0 && n > 0){
            return AkkermanFunc(m-1, AkkermanFunc(m, n-1));
        } else {
            return -100000000;
        }
    }

    public String isPowOf2(int N){
        if(N == 2){
            return "YES";
        }
        if(N < 2) {
            return "NO";
        } else {
            return isPowOf2(N/2);
        }
    }

    public int sumOfDigits(int N){
        if(N <= 0){
            return N;
        }
        return sumOfDigits(N / 10) + (N % 10);
    }

    public String digitsRtl(int N){
        if(N < 10){
            return N + "";
        }
        return N%10 + " " + digitsRtl(N/10);
    }

    public String digitsLtr(int N){
        if(N < 10){
            return N + "";
        }
        return digitsLtr(N/10) + " " + N%10;
    }

    //D should be 1, cuz we can't divide by zero
    public String checkSimplicity(int N, int D){
        if(N%D == 0 && D != 1){
            return "NO";
        }
        if(D == N/2) {
            return "YES";
        }
        return checkSimplicity(N, D+1);
    }

    public void printSimpleDividers(int n, int k){
        if (k > n / 2) {
            System.out.println(n);
            return;
        }
        if (n % k == 0) {
            System.out.println(k);
            printSimpleDividers(n / k, k);
        }
        else {
            printSimpleDividers(n, ++k);
        }
    }

    public String isAPalindrome(String s, int l, int r){
        //Defines the exit condition
        if(l > r){
            return "YES";
        }
        if(s.charAt(l) != s.charAt(r)){
            return "NO";
        }
        //Defines the recursion step
        return isAPalindrome(s, l+1, r-1);
    }

}
