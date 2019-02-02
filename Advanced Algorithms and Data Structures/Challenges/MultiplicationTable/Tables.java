/*
Author:		Juan José Méndez Torrero
S-number:	s3542416
Date:		19-02-2018
Challenge: 	Multiplication Table

Description:
This program create first a matrix of size NxM. The value of each element of the matix is row*column.  
When it finished, I pass it to an array of integers. Then, I sort it with the Insertion sort algorithm.
Finally, I print the Kth array's element.

Time complexity: O(n^2)

Memory complexity: O(1)

*/

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Tables{

	public static int getKTh(int[] vector, int k)
	{
		int i, j=0;
		int greater = 0;
		int key=0;
		
		//I'm going to use Insertion sort.		
		for (i=1; i<=(vector.length)-1; i++) {
			key = vector[i];	
			j = i-1;
			while(i>0 && vector[j]>key)
			{
				vector[j+1] = vector[j];
				j=j-1;
			}
			vector[j+1]=key;
		}
		
		/*
		for (i=0;i<=vector.length-1 ; i++) {
				System.out.print(vector[i] + " ");
			}
		*/
		greater = vector[k-1]; //It has to be k-1 due to the vector starts in 0.
		return greater;
	}


	public static void main(String[] args) {
		if (args.length<2) {
			System.out.print("Error in the command line\nTry with: java Tables n m k\n");
		}
		else
		{
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			String input = br.readLine();
			int w=0;
			for (String value : input.split(" ") ) {
				input[w++] = Integer.parseInt(value);

			}
			int n = input[0];
			int m = input[1];
			int k = input[2];
			int[][] matrix = new int[n][m];
			int[] vector = new int[n*m];
			int i, j, l=0;
			int kthGreater=0;

			for (i=0; i<=n-1; i++) {
				for (j=0; j<=m-1;j++ ) {

					matrix[i][j] = ((i+1)*(j+1));
					vector[l] = matrix[i][j];
					l++;					
				}
			}
			kthGreater = getKTh(vector, k);
			System.out.print("The Kth greater number is: " + kthGreater + "\n");
		}
	}
}