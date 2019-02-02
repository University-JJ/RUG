function g = reduce(f,sigma)
    g = imgaussfilt(f,sigma);
	g = IPdownsample(g,2);
end