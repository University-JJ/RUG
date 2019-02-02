#include<bits/stdc++.h>
using namespace std;

//returns the length of final list 
long long count(long long temp) 
{
  long long x = 1;
  while(temp > 1)
  {
    temp /= 2;
    x *= 2;
  }
  return 2 * x - 1;
}

int is_one(long long pos, long long target, long long num)
{ 
  // end the recursion
  if(num < 2)
    return num;
  
  // if you are in the middle just output the residual mod 2
  if(pos + 1 == 2 * target)
    return num % 2;

  // otherwise reduce the problem to a smaller one
  num /= 2;
  pos /= 2;   

  /* right part is equivalent to left part because of symmetrical tree, 
    so just go to the left part. */
  if(target > pos + 1)
      target -= (pos + 1);      

  // iterate into the small problem
  return is_one(pos, target, num);
}

void solve(){
  long long l, r ,n ,x , ans = 0, i;
  cin >> n >> l >> r;

  // set x to the length of the final list
  x = count(n);

  // Just iterate through the subsegment
  for(i = l; i <= r; ++i)
    ans += is_one(x, i, n);

  cout << ans << endl;
}

int main()
{
    cout<<setprecision(6)<<fixed;
    ios_base::sync_with_stdio(0);
    cin.tie(0);
 
    solve();

    return 0;
}

