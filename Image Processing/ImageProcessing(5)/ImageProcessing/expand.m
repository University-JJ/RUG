function g = expand(f,sigma)
g = IPzoom(f,2);
g = imgaussfilt(g,sigma);
end