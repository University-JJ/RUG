/*
Author:		Juan Jose Mendez Torrero
S-number:	s3542416
Date:		19-02-2018
Challenge: 	Multiplication Table

Description:
This program create first a matrix of size NxM. The value of each element of the matix is row*column.  
When it finished, I pass it to an array of integers. Then, I sort it with the Insertion sort algorithm.
Finally, I print the Kth array's element.

*/

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class TABLE{
	/*This method gets the Kth greater number in a vector of integers.
	To solve this, we use insertion sort to reorder the vector to make the 
	search easier
	*/
	public static int getKTh(int[] vector, int k){
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
		
		greater = vector[k-1]; //It has to be k-1 due to the vector starts in 0.
		return greater;
	}

	/*This method reads the input we use the BufferedReader to reads all
	the values at once.*/
	public static int[] readBuffer(){
		String strLine = "";
	    int[] input = new int[3];
	    int i = 0;
	    try {
	        InputStreamReader isr = new InputStreamReader(System.in);
	        BufferedReader br = new BufferedReader(isr);
	        strLine = br.readLine();
	        for (String value : strLine.split(" ")) {
			    input[i++] = Integer.parseInt(value);
			}
	        isr.close();
	    } catch (IOException ioe) {
	        ioe.printStackTrace();
    	}
    	return input;
	}

	public static void main(String[] args) {
		int[] input = readBuffer();
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
		System.out.print(kthGreater + "\n");
	}
}