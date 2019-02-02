function img =  IPMarrHildreth(f,sigma,p) 
     n = ceil(sigma * 6/2)+2;
     m = ones(n,n);
     
     for y=1:n
         for x=1:n
           r2 = (x-1)^2+(y-1)^2;
           m(y,x) = -1*(2/(pi*(sigma^4))) * (1-r2/(2*(sigma^2))) * exp(-r2/((2*sigma^2)));
         end
     end
     m = [m(end:-1:2,end:-1:2),m(end:-1:2,:);m(:,end:-1:2),m];
    
    f = filter2(m,f);
    img = f >= max(max(f))*(p/100);
end