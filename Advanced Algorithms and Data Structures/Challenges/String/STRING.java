/*
Author:		Juan Jose Mendez Torrero
S-number:	s3542416
Date:		2-03-2018
Challenge: 	Very nice String

Description:
This program takes a sequence of letters, ai until ||s||-ai+1(this values are
decremented one unit due to char problems), and reverse this sequence. This
process is going to be repeated m times.
*/

import java.util.*;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;


public class STRING{


	public static char[] reverseString(char[] original, int pos){
		int endPos= (original.length)-pos;
		pos--;
		for (int i = endPos; i>= original.length/2;i-- ) {
			char aux = original[i];
			original[i]=original[pos];
			original[pos]=aux;
			pos++;
		}
		return original;
	}


	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String letters = "";
		int m=0;
		try{
			String[] input = br.readLine().split(" ");
			letters = input[0];//String s
			int w=0;
			input = br.readLine().split(" ");
			m=Integer.parseInt(input[0]);//Number of days
			ArrayList<Integer> a = new ArrayList<Integer>();//ai query
			String strLine=br.readLine();
			for (String value : strLine.split(" ")) {
				a.add(Integer.parseInt(value));
			}
			if (a.size()>m || a.size()<m) {
				System.out.print("More/Less number than requested\n");
				System.exit(0);
			}

			char[] original = letters.toCharArray();
			for (int i=0;i<m ;i++ ) {
				int pos=a.get(i);
				original = reverseString(original, pos);
			}
			for (int j=0;j<original.length ;j++ ) {
				System.out.print(original[j]);
			}
			System.out.print("\n");		

		}catch (IOException e){
			e.printStackTrace();
		}
	}
}