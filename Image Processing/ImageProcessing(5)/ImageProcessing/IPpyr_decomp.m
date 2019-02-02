function g = IPpyr_decomp(f,J,sigma)
f = im2double(f);       % Convert from (8-bit) integer to double values.
M = size(f,2);
P = M * (2-2^(1-J));    % Height of laplacian pyramid.
g = ones(P,M);          % Initialize laplacian pyramid.

m = M;                  % width/height of the newly drawn.
p = 1;                  % y-coordinate of top border of the newly drawn.
fj = f;

for j=1:J
    fj1 = reduce(fj,sigma);
    dj1 = fj - expand(fj1,sigma);
    
    if j == J
        g(p:p+m-1,1+M/2-m/2:m+M/2-m/2) = fj;
    else
        g(p:p+m-1,1+M/2-m/2:m+M/2-m/2) = dj1;
    end
    
    p = M * (2-2^(1-j))+1;
    m = m/2;
    
    fj = fj1;
    dj = dj1;
end
end



