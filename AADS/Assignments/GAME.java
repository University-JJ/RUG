//Ashton Spina 26/02/2018

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Math;

/*
    Time Complexity : O(max(s_i))
        The algorithm needs to iterate through all the values in the histogram.  Therefore
        the complexity will have an upper bound of the maximimum input value.  There's a definite
        lower bound of the number of elements that have to be counted which is n.  This solution simply
        implements the array with the maximum possible value and calculates a flat amount of calculations
        per solution.  You can imagine a solution with a hashmap or similar might have a complexity nearer to simply exactly O(n) in n input 
        values that need to be hashed and would be the only solution with utility for very large input
        values.

    Space Complexity : O(max(s_i))
        The algorithm also only needs to store up to the maximum value in the histogram.  
        Therefore, the complexity arguments of the time complexity apply exactly the same here.

        A hashmap solution might only have to store the number of unique keys, but its still
        an upper bound storage of O(n)
*/

class GAME {
    /**  Again the points array is allocated space inefficiently.  
      The method checks whether for the current state, it would be better to keep the previous
      state or twice previous state + the sum from the current number.  As you can see 
      this dynamic approach allows the maximum sum to be selected.  */
    public static long solve(long histogram[]) {
        long[] points = new long[100001];
        points[1] = Math.max(histogram[0], histogram[1]);
        for(int i = 2; i < 100001; ++i){
            points[i] = Math.max(points[i - 1], points[i - 2] + (i * histogram[i]));
        }
        return points[100000];
    }
    /**  The main method reads in  the input and stores it in a histogram
      of the size of the maximum possible input.  A more space efficient solution
      would be to find the maximum value of the array before creating the histogram
      or using a hashmap at the sacrifice of a small amount of performance, 
      but for simplicity in this case simply the largest input has 
      been allocated.  The histogram is then sent to be solved.*/
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            long n = Integer.parseInt(br.readLine());
            long[] histogram = new long[100001];
            String[] input = br.readLine().split(" ");
            for (int i = 0; i < n; ++i)
                ++(histogram[Integer.parseInt(input[i])]);
            System.out.println(solve(histogram));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}