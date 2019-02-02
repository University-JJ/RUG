/*
Author:		Juan Jose Mendez Torrero
S-number:	s3542416
Date:		10-3-2018
Challenge: 	Walk

Solution's description:
To solve this problem, first we create all nodes with each correspondat value.
Then, we complete this information by adding to each node each parent, knowing the
actual relationship between all nodes in advance.

Time complexity:
Let n be the number of nodes that form the tree.
The size of the tree then is lg(n). To solve this problem we have to loop over the
tree, performig the operations. Furthermore, to know the total value between the nodes 
a and b , we have to loop over the tree just in the path a-b.
Then the time complexity is O(D(a,b)*n*lg(n))
D(a, b) represent the number of time that we have to do an operation.

Memory complexity:
We have to store the tree as well as the nodes. They have O(lg(n)) and O(1). So the 
memory complexity is O(lg(n))
*/


import java.util.*;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class WALK{

    static class Node{
        int key;
        Node parent;
        
        Node(int key){
            this.key=key;
            parent=null;
        }
    }

    public static Node returnFather(Node[] n, int posFather, int[]fathers){
        Node aux=null;
        aux=n[fathers[posFather-1]-1];
        return aux;
    }
    
    public static void main(String[] args) {
        /*READ INFO*/
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n=0;//Number of nodes
        int prime=0;//Prime number
        int q=0;//Number of question
        try{
			String[] input =br.readLine().split(" ");
            n=Integer.parseInt(input[0]);
            prime=Integer.parseInt(input[1]);
            q=Integer.parseInt(input[2]);
            int[] nodes = new int[n];
            int[] fathers = new int[n-1];
            int[][] questions = new int[q][2];

            int i=0, j=0;

            String strLine =br.readLine();
            for(String value : strLine.split(" ")){
                nodes[i++] = Integer.parseInt(value);
            }
            String strLine2 = br.readLine();
            i=0;
            for(String value : strLine2.split(" ")){
                fathers[i++] = Integer.parseInt(value);
            }
            String strLine3;
            i=0;
            while (i<q) {
                strLine3 = br.readLine();
                j=0;
                for(String value : strLine3.split(" ")){
                    questions[i][j++] = Integer.parseInt(value);
                }
                i++;
            }


            /*PROGRAM'S BEGINING*/
           Node[] data = new Node[n];
           int w=0;
           for (int a : nodes) {
               data[w++]= new Node(a);
           }
           /*CALCULATE THE FATHER OF EACH NODE*/
           for(int a=0;a<n-1;a++){
               int child = a+1;
               data[a+1].parent=returnFather(data, child, fathers);
           }
           /*CALCULATE IF THE TOTAL AMOUNT OF THE NODES STEPPED IS DIVIBLE BY prime*/
            for(int a=0; a<q ; a++){
                int endPos=questions[a][1]-1;
                int intialPos=questions[a][0]-1;

                Node endTree = data[endPos];
                Node root = data[intialPos];

                int totalCount=0;
                while(endTree!=root){
                    totalCount=totalCount+endTree.key;
                    endTree=endTree.parent;
                }
                totalCount+=endTree.key;
                if((totalCount%prime)==0){
                    System.out.print("YES\n");
                }
                else{
                    System.out.print("NO\n");
                }
            }
        }catch (IOException e){
			e.printStackTrace();
        }  
    }
    
}