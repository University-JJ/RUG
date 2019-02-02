function g2 = IPpyr_recon(g,J,sigma)
M = size(g,2);
P = size(g,1);
d = 0;
for j=J-1:-1:0
    p = M * (2-2^(1-j-1));
    w = 2^(log(M)/log(2)-j);
    m = M/2 - w/2;
    
    if j == J-1
        f = g(p-(w-1):p,m+1:m+w);
    else
        d = g(p-(w-1):p,m+1:m+w);
        f = expand(f,sigma) + d;
    end
end
g2 = f;
end