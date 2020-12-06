package main.java.themes.ArraysAndStrings.LeetCode;

public class WaterContainer {
    //Given n non-negative integers a1, a2, ..., an ,
    //where each represents a point at coordinate (i, ai).
    //n vertical lines are drawn such that the two endpoints
    //of the line i is at (i, ai) and (i, 0).
    //Find two lines, which, together with the x-axis forms
    //a container, such that the container contains the most water
    //
    //Notice that you may not slant the container.

    //The task consists of two points:
    //1) Calculate the square
    //2) Select for which borders with will calculate the square
    //What is not changeable here is how do we calculate the square

    //Brute force approach:
    //We will compare all of the borders to all of the borders using
    //for(int i=0; ...), for(int j=0; ...);

    //Optimized approach:
    //We will use 2 pointers to go over the array.
    //The container with the biggest sq. will be the one with
    //Most wide-spread borders and/or most high borders.
    //So the approach here is that we start with the two ends of the
    //array covering most widespread case. If the solution is not here,
    //then it is somewhere in the middle.
    //We calculate the square for i = 0 and j = arr.length
    //And what happens next is that we move the pointer of the lowest
    //border in. Because the size of container is constrained to the
    //lowest border and by doing that we are trying to find a taller
    //replacement for it. And repeat the algorithm, saving the
    //the biggest square result

    public int sq(int i, int j, int v1, int v2){
        if(v2-v1 > 0){
            return (j-i)*v1;
        } else {
            return (j-i)*v2;
        }
    }

    public int maxArea(int[] height) {
        int i = 0;
        int j = height.length-1;
        int sqMax = 0;
        while(i != j){
            if(sqMax < sq(i, j, height[i], height[j])){
                sqMax = sq(i, j, height[i], height[j]);
            }
            if(height[i] < height[j]){
                i++;
            } else {
                j--;
            }
        }
        return sqMax;
    }

    public static void main(String[] args){}
}
