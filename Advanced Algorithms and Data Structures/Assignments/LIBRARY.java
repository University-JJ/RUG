//Ashton Spina 26/02/2018

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
    Time Complexity : O(n)
        For the input size n, each of the n elements is inspected one on input and then
        again when the book numbers are checked. Although for each element there are
        many if statements, this coefficient multiplied by n is still O(n).

    Space Complexity : O(n)
        There are multiple arrays of size n storing values in memory.  This multiple 
        multiplied by n is still a coefficient of n and therefore O(n).
*/

public class LIBRARY {

    /** This method simply prints each book number in the library
    with a space character after each and then finally a new line */
    public static void printNewCatalogue(int[] library, int n){
	for(int i = 0; i < n; i++) {
	    System.out.print(library[i] + " ");
	}
	System.out.println();
    }

    /** This function checks each book position in the library. If its a position that was previously
    marked 0 as the booknumber was larger than its position number, then the next available nubmer
    is given to that book, as checked by the histogram.  Otherwise, if the histogram, having the count of 
    books with that booknumber be greater than 1, would imply that at least one of the books of that number
    needs their number changed.  The j value represents a free value in the histogram.  If that 
    available value in the histogram is greater than the one it would be replacing, simply skip that
    value and mark it as checked, otherwise the count of that number in the histogram should
    be decreased and the book number assigned in the library*/
    public static void checkBookNumbers(int[] library, int[] histogram, int n){
	int i, j = 0;
        boolean[] checked = new boolean[n];

        for(i = 0; i < n; ++i){
            if(library[i] == 0){
                while(histogram[j] != 0) 
                	++j;
                library[i] = ++j;
            }
            else if(histogram[library[i] - 1] > 1){
                while(histogram[j] != 0) 
                	++j;
                if(j + 1 > library[i] && !checked[library[i] - 1])
                    checked[library[i] - 1] = true;
                else {
                    --histogram[library[i] - 1];
                    library[i] = ++j;
                }
            }
        }
    }

    /** This method reads the input values.  If the booknumber is greater than the book's
    position in the library, it will have to be assigned a new value because
    its not possible that all the numbers less than that booknumber have been used yet.  As
    such the position in the library is marked 0 for future calculation.  Otherwise, the book
    is added to the current library catalogue and the count of that book in the histogram
    is increased.  */
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String[] input = reader.readLine().split(" ");
            int n = Integer.parseInt(input[0]), i = 0, j= 0;
            input = reader.readLine().split(" ");
            int[] library = new int[n];
            int[] histogram = new int[n];

            for (i = 0; i < n; ++i) {
                int bookNumber = Integer.parseInt(input[i]);
                if (bookNumber > n) {
                    library[i] = 0;
                } else {
                    ++histogram[bookNumber - 1];
                    library[i] = bookNumber;
                }
            }

            checkBookNumbers(library, histogram, n);
            printNewCatalogue(library, n);
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
}
