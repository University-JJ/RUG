function img = IPgradientthresh(f,p)
    nRows = size(f,1);
    nCols = size(f,2);

    % masks
    
    X = [0,0,0;-1,0,1;0,0,0];
    Y = X';
    
    % Compute gradient image
    fx = IPfilter(f,X);
    fy = IPfilter(f,Y);
    g =  abs(fx) + abs(fy);
    img = g >= max(max(g))*(p/100);
end