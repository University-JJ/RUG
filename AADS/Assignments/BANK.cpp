#include <bits/stdc++.h>
#include <time.h>
#define l(x) 2*x
#define r(x) (2*x+1)
using namespace std;
typedef long long LL;
const int NMAX = 300505;
const int inf = 1e9;
const int dx[] = {0, 0, -1, 1};
const int dy[] = {1, -1, 0, 0};

int n, m, k, x, a, b, c;
int A, B;
int distA[NMAX], distB[NMAX];
vector<pair<int,int>> G[NMAX];

void dijkstra(int v, int *arr){

    // Initial conditions
    for(int i=1; i<=n; ++i){
        arr[i] = inf;
    }
    arr[v] = 0; // Set starting position
    set<pair<int,int>> T; // Priority queue

    /*
     * T contains pairs of the form (distance_to_the_node, index_of_the_node)
     */
    T.insert({0, v}); // Set the starting index

    while(!T.empty()){
        int x = (*T.begin()).second; // Choose the node with least distance so far
        T.erase(T.begin());
        for(int i=0; i<G[x].size(); ++i) // Update node x
            if(arr[G[x][i].first]>arr[x]+G[x][i].second) {
                arr[G[x][i].first] = arr[x] + G[x][i].second; // Make distance shorter
                T.insert({arr[G[x][i].first], G[x][i].first}); // Add to the queue
            }
    }
}

int main(){
    cin.tie(0);
    ios_base::sync_with_stdio(0);
    cin >> n >> m >> k >> x;

    vector<int> banks;
    for(int i=1; i<=k; ++i){
        cin>>a;
        banks.push_back(a);
    }

    for(int i=1; i<=m; ++i){
        cin >> a >> b >> c;
        G[a].push_back({b, c});
        G[b].push_back({a, c});
    }

    cin >> A >> B;

    dijkstra(A, distA);
    dijkstra(B, distB);

    int prev = -1;
    for(auto el: banks){
        if(distB[el] < x && distA[el] != inf){
            /*
             * If the police didn't caught us and we
             * can reach the bank from city A
             */
            if(prev == -1)
                prev = distB[el] + distA[el];
            else
                prev = min(prev, distB[el] + distA[el]);
        }
    }
    cout<<prev<<"\n";

    return 0;
}
