#include<bits/stdc++.h>
using namespace std;

/*iterate through each row, sum the number of elements on each row
 that are strictly smaller than x which are either the whole row, 
 or all until the (x - 1) / i'th element. */
long long count(long long x, int n, int m){
    long long res = 0;
    for(int i = 1; i <= n; ++i) 
    	res += min((long long)m, (x - 1) / i);
    return res;
}

void solve(){
  long long n, m, k;
  cin >> n >> m >> k;
  // The bounds for the binary search
  long long l = 1, r = n * m + 1;
  // Binary search on the magnitude of the k'th element in the matrix.
  while(l < r){
    /* The number of elements in the matrix which are strictly smaller 
     than k'th number, has to be smaller than k. Choose the biggest element 
     that satisfies the above condition. */
  	long long x = (l + r) / 2;
    if(count(x, n, m) < k) 
    	l = x + 1; 
    else 
    	r = x;
  }
  cout<< l - 1 <<endl;
}

int main()
{
    cout<<setprecision(6)<<fixed;
    ios_base::sync_with_stdio(0);
    cin.tie(0);
 
    solve();

    return 0;
}

