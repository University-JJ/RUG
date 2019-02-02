//Ashton Spina 26/02/2018

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Math;

/*
    Time Complexity : O(n * log_2(n) + m * log_2(n))
        The n inputs are read in and saved and for each input a log_2(n)
        operation takes place to calculated log_2(n) ancestors.

    Space Complexity : O(n * log_2(n))
        For each of n inputs, log_2(n) ancestors are calculated.
*/

public class ANCESTORS {

    /** The main method reads inputs for n and m.
      It then takes in n inputs and stores it in a matrix of (log_2(n) + 1) * (n + 1),
      which the + 1 account for the indexing of the inputs.  The inputs are stored in the 
      first row of the matrix.  The ancestors are calculated for each of the inputs 
      and then the queries called.*/
    public static void main(String args[]) {  
        int n, m;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String[] input = br.readLine().split(" ");
            n = Integer.parseInt(input[0]);
            m = Integer.parseInt(input[1]);
            input = br.readLine().split(" ");
            int[][] dp = new int[(int)((Math.log(n) / Math.log(2))) + 1][n + 1];
            for (int i = 1; i <= n; ++i)
                dp[0][i] = Integer.parseInt(input[i - 1]);
            
            calculateAncestralLevels(dp, n);
            for(int i = 0; i < m; ++i){
                input = br.readLine().split(" ");
                processQuery(dp, Integer.parseInt(input[0]), Integer.parseInt(input[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** This method calculates ancestors of each input.  The ancestors are calculated
      at levels of powers of 2 (represented by the bitshifted 1) in order to allow each query to 
      be answered in log_2(n) time.*/
    public static void calculateAncestralLevels(int[][] dp, int n){
        for(int level = 1; (1 << level) <= n; ++level)
            for(int i = 1; i <= n; ++i)
                dp[level][i] = dp[level - 1][dp[level - 1][i]];
    }

    /** This method calculates the p-th ancestor of q.  This is done by checking
    for each power of 2 up to and including p, if there exists an ancestor for that 
    level until no more ancestors are found.*/
    public static void processQuery(int[][] dp, int q, int p){
        for(int i = 0; (1 << i) <= p; ++i){
            if((p & (1 << i)) > 0)
                q = dp[i][q];
        }
        System.out.println(q);
    }
}


