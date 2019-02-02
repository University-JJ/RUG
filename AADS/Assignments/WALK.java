import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
    Time Complexity : O(n + q)
        For the input size of n nodes and q queries, each node n needs multiple operations, 
        but nonetheless the operations are constant per node.  This is a linear number of
        of operations in n.  There is also a constant series of operations per query which means
        there is also a linear number of operations in the number of queries.

    Space Complexity : O(n)
        For n input values, a relevant value is stored in one of each 5 arrays.  Therefore,
        there is some coefficient of n and a constant value of integers to be stored in memory 
        and the overall order is linear in the input size n.
*/


public class WALK {

    /** In this method, the input values and their parents are stored.  This is a tree of 
    sorts and could be represented as a tree structure with nodes and such, but its simpler
    and faster to represent the nodes in arrays.  Powers of 10 modulus the k value are 
    precomputed and stored in an array.  These can be used for the different depths of the tree.
    The depth value also precomputes the depths of each node by adding 1 to the depth of each node's
    parent node.  Strictly speaking, the depths  array is not necessary to
    compute the final values and could be traded for more computations per query, so 
    this is a tradeoff in space for time. In order to calculate whether a query's path modulus
    the k value is in fact 0, all one has to do is subtract the hash value of the parent of a multiplied 
    by the powersOfTen value of the difference in depth of a and b from the hash value of b.  This essentially
    extracts only the subpath of the path from the root to b that is the path from a to b 's 
    value.  Because each hashK value was precomputed by multiplying by 10 you essentially form
    a value representing the path from a to b that can be taken modulus k to check the final result.

    **/
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String[] input = br.readLine().split(" ");
            int n = Integer.parseInt(input[0]);
            int k = Integer.parseInt(input[1]);
            int q = Integer.parseInt(input[2]);

            int[] inputTree = new int[n + 1];
            int[] parents = new int[n + 1];
            int[] powersOfTen = new int[n + 1];
            int[] depth = new int[n + 1];
            int[] hashK = new int[n + 1];

            powersOfTen[0] = 1;
            input = br.readLine().split(" ");
            for (int i = 1; i <= n; ++i) {
                inputTree[i] = Integer.parseInt(input[i - 1]);
                powersOfTen[i] = (10 * powersOfTen[i - 1]) % k;;
            }

            input = br.readLine().split(" ");
            for (int i = 1; i < n; ++i) {
                parents[i + 1] = Integer.parseInt(input[i - 1]);
            }

            depth[1] = 0;
            hashK[1] = inputTree[1];
            for (int i = 2; i <= n; ++i) {
                hashK[i] = (hashK[parents[i]] * 10 + inputTree[i]) % k;
                depth[i] = depth[parents[i]] + 1;
            }

            for (int i = 0; i < q; i++) {
                input = br.readLine().split(" ");
                int a = parents[Integer.parseInt(input[0])];
                int b = Integer.parseInt(input[1]);
                System.out.println((hashK[b] - hashK[a] * powersOfTen[depth[b] - depth[a]] % k != 0) ? "NO" : "YES");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
