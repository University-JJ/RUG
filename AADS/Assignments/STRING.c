/* Daan Raatjes */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define SIZE 200100

/* To solve this problem efficiently, two observations have to be made.
 * 
 * First:
 * If there is a reverse operation at position X, and later there is another
 * reverse operation at position X, the operations cancel each other out.
 * 
 * Second:
 * If there is a reverse operation at position X and another at position 
 * X + k, Only the first and last k characters have to be swapped, as 
 * the other swaps cancel each other out.
 * 
 * 
 * Time complexity:
 * let n be the number of queries.
 * let k be the size of the string.
 * The size of the histogram is only k / 2. To solve the problem we have
 * to loop over the histogram, performing only constant operations. To
 * construct the histogram we have to loop over all queries.
 * Therefore, the time complexity is O(n + k).
 * 
 * Memory complexity:
 * We have to store the string as well as the histogram. Both are are
 * in O(k). Therefore, the memory complexity is O(k).
 * 
 */


// Function to swap two characters.
void swap(char *a, char *b) {
	char h = *a;
	*a = *b;
	*b = h;
}

// Swap all characters in the string based on the histogram
void solve(char *str, int stringLen, int *hist) {
	// Variable to indicate whether there was a query before that
	// cancels out the current query.
	int previous = 1;
	/* Loop over the histrogram,
	 * accessing the queries in ascending order
	 */
	for(int i = 0; i < stringLen / 2; ++i) {
		/* If the value in the histogram plus previous is even,
		 * then the operation is not cancelled.
		 */ 
		if ((hist[i] + previous) % 2 == 0)
			swap(&str[i], &str[stringLen - i - 1]);
		// If the histogram value is odd, increment previous by 1.
		previous += hist[i] % 2;
	} 
	
	printf("%s\n", str);
}

int main(int argc, char **argv) {
	char *str = malloc(sizeof(char) * SIZE);
	int n, idx = 0;
	char c;
	while((c = getchar()) != '\n') 
		str[idx++] = c;
		
	str[idx] = 0;
	int stringLen = idx;
	// The histogram stores all queries
	int *hist = calloc(stringLen / 2 + 1, sizeof(int));
	scanf("%d", &n);
	
	/* Create a histogram during the scanning of the input
	 * We can use this histrogram to access the queries in a
	 * sorted manner. 
	 */
	for(int i = 0; i < n; ++i) {
		scanf("%d", &idx);
		hist[idx - 1]++;
	}
	
	solve(str, stringLen, hist);
	
	free(hist);
	free(str);
	return 0;
}

