#include <bits/stdc++.h>
#include <time.h>
#define l(x) 2*x
#define r(x) (2*x+1)
using namespace std;
typedef long long LL;
const int NMAX = 100505;
const int inf = 1e9;
const int dx[] = {0, 0, -1, 1};
const int dy[] = {1, -1, 0, 0};

int T;
int n, m;
bool possible;
int viz[NMAX], colored[NMAX];
vector<int> G[NMAX];

int comp_size, col_size;
void color(int v, int col){
    stack<pair<int, int>> S;

    S.push({v, col});
    while(!S.empty()) {
        v = S.top().first;
        col = S.top().second;
        S.pop();
        comp_size++;
        col_size += (col == 0);

        for (auto el: G[v]) {
            if (viz[el]) {
                if (colored[el] != 1 - col) {
                    possible = 0;
                }
            } else {
                viz[el] = 1;
                colored[el] = 1 - col;
                S.push({el, 1 - col});
            }
        }
    }
}

int main(){
    cin.tie(0);
    ios_base::sync_with_stdio(0);
    cin >> T;
    for(int t=1; t<=T; ++t){
        cin >> n >> m;
        possible = 1;

        for(int i=1; i<=m; ++i){
            int a, b;
            cin >> a >> b;
            G[a].push_back(b);
            G[b].push_back(a);
        }

        int rs = 0;
        for(int i=1; i<=n; ++i){
            if(!viz[i]){
                comp_size = 0;
                col_size = 0;
                // Try the first color for the connected component
                // The color is either 0 or 1
                viz[i] = 1;
                colored[i] = 0;
                color(i,  0);

                // Either the first coloring or its flipped equivalent
                rs += max(col_size, comp_size - col_size);
            }
        }

        if(!possible){
            cout<<"NO\n";
        }else{
            cout<<rs<<"\n";
        }

        for(int i=1; i<=n; ++i){
            G[i].clear();
        }
        memset(colored, 0, sizeof(colored));
        memset(viz, 0, sizeof(viz));
    }

    return 0;
}
