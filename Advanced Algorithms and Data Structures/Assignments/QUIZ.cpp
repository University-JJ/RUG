#include <bits/stdc++.h>
using namespace std;
typedef long long LL;
const int mod1 = 1000000000 + 7;
const int NMAX = 200555;

struct item {
    int key, prior, cnt, dup;
    item *l, *r;
    item (int key, int prior): key(key), prior(prior), l(NULL), r(NULL) {
        this->cnt = 1;
        this->dup = 1;
    }
};

typedef item* pitem;

int cnt(pitem t){
    return t ? t->cnt : 0;
}

void upd_cnt(pitem t){
    if(t){
        t->cnt = t->dup + cnt(t->l) + cnt(t->r);
    }
}


void split(pitem t, int key, pitem &l, pitem &r){
    if(!t)
        l = r = NULL;
    else if (key < t->key)
        split(t->l, key, l, t->l), r = t;
    else
        split(t->r, key, t->r, r), l = t;

    upd_cnt(t);
}

void merge(pitem &t, pitem l, pitem r){
    if(!l || !r)
        t = l ? l : r;
    else if (l->prior > r->prior)
        merge(l->r, l->r, r), t = l;
    else
        merge(r->l, l, r->l), t = r;

    upd_cnt(t);
}

pitem find(pitem t, int key){
    if(!t)
        return t;
    else if(t->key == key)
        return t;
    else
        return find(key < t->key ? t->l : t->r, key);
}

void increment(pitem t, int key){
    if(t->key == key)
        t->dup++;
    else
        increment(key < t->key ? t->l : t->r, key);
    upd_cnt(t);
}

void erase(pitem &t, int key){
    if(!t){
        cout<<"err";
    }
    else {
        if (t->key == key) {
            if (--(t->dup) == 0) {
                //delete(t);
                merge(t, t->l, t->r);
            }
        } else
            erase(key < t->key ? t->l : t->r, key);
    }
    upd_cnt(t);
}

void insert(pitem &t, pitem it){
    if(!t)
        t = it;
    else if (it->prior > t->prior)
        split(t, it->key, it->l, it->r), t = it;
    else
        insert(it->key < t->key ? t->l : t->r, it);
    upd_cnt(t);
}

int descend(pitem t, int sum){
    if(!t){
        cout<<"err"<<"\n";
    }
    if(sum > cnt(t->l)){
        sum -= cnt(t->l);
        if(sum > t->dup){
            sum -= t->dup;
            return descend(t->r, sum);
        } else {
            return t->key;
        }
    } else {
        return descend(t->l, sum);
    }
}

int n, q, ans[200555];


int main (){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    pitem R = NULL;
    vector<tuple<int,int,int>> sortme;

    cin >> n;

    for(int i=1; i<=n; ++i)
    {
        int a, b;
        cin >> a >> b;
        sortme.push_back(make_tuple(a, 1, b));
        sortme.push_back(make_tuple(b, 1, a));
    }

    cin >> q;

    for(int i=1; i<=q; ++i){
        int x, k;
        cin >> k >> x;
        sortme.push_back(make_tuple(x, k, i));
    }
    sort(sortme.begin(), sortme.end());

    for(auto tup: sortme){
        int a, b, c;
        tie(a, b, c) = tup;
        if(b == -1){
            int l = c-a+1;
            if(find(R, l)) {
                increment(R, l);
            } else {
                pitem add = new item(l, rand()%mod1);
                //cout<<add<<" "<<NULL<<"\n";
                insert(R, add);
            }
        }
        if(b > 0 && b < n+1){
            if(cnt(R) < b)
                ans[c] = -1;
            else
                ans[c] = descend(R, b);
        }
        if(b == n+1){
            int l = a-c+1;
            //cout<<"erase "<<l<<" ";
            erase(R, l);
        }
    }

    for(int i=1; i<=q; i++){
        cout<<ans[i]<<"\n";
    }

    return 0;
}
