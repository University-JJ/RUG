import java.util.*;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
public class AKU{

	public static ArrayList<Integer> hitMove(ArrayList<Integer> jacksHits, int i)
	{
		ArrayList <Integer> vector=new ArrayList<Integer>();
		int aux1=0, aux2=0, aux3=0, finalElement=0;

		aux1=jacksHits.get(i)/2;
		aux2=jacksHits.get(i)%2;
		aux3=jacksHits.get(i)/2;
		jacksHits.remove(i);
		
		
		vector.add(0, aux1);
		vector.add(1, aux2);
		vector.add(2, aux3);
		jacksHits.addAll(i, vector);
		
		return jacksHits;
	}

	public static int[] readBuffer(){
		String strLine = "";
	    int[] input = new int[3];
	    int i = 0;
	    try {
	        InputStreamReader isr = new InputStreamReader(System.in);
	        BufferedReader br = new BufferedReader(isr);
	        strLine = br.readLine();
	        while((strLine = br.readLine()) != null){
			    input[i++] = Integer.parseInt(strLine);
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
		int r = input[1];
		int l = input[2];
		
		ArrayList<Integer> jacksHits = new ArrayList<Integer>();
		jacksHits.add(n);

		int lastIndex = 0;
		int move=0;
		int i=0, j;

		for (i=0;i<jacksHits.size();i++ ) {
			if (jacksHits.get(i)>1) {
				jacksHits = hitMove(jacksHits, i);
				i=-1;
			}
		}
		System.out.print(jacksHits);
		/*we have to get the index of the last element to know which size it has*/
		for (i=r;i<=l ; i++) {
			if (jacksHits.get(i)==1) {
				move++;
			}
		}
		System.out.print(move + "\n");
	}
}