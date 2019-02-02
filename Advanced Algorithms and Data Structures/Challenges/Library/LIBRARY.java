import java.util.*;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class LIBRARY{

	public static void sortedBooks(int[] books, int n){
		
		int[] rpt = new int[n+1];
		for (int i=0;i<n ;i++ ) {
			for (; ; ) {
				
			}
		}

		int num=1;
		Boolean[] visited = new Boolean[n+1];
		Arrays.fill(visited, Boolean.FALSE);
		for (int i=0;i<n ; i++) {
			
			while(rpt[num]>=1)
				num++;

			if (num>books[i] && !visited[i]) {
				visited[i]=true;
			}
			else{
				--rpt[i];
				books[i]=num;
				num++;
			}
		}

		for (int i=0;i<n ; i++) {
			System.out.print(books[i]+" ");
		}
		System.out.print("\n");

	}

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n=0;//Numbers of books
		
		try{
			String[] input =br.readLine().split(" ");
			n=Integer.parseInt(input[0]);
			int[] books = new int[n];
			int i=0;
			String strLine = br.readLine();
			for (String value : strLine.split(" ")) {
				books[i++] = Integer.parseInt(value);
			}
			if (books.length!=n) {
				System.out.print("More/Less number than requested\n");
				System.exit(0);
			}

			sortedBooks(books, n);

		}catch (IOException e){
			e.printStackTrace();
		}
	}
}