function B = IPcontour(f)
    img = [ones(size(f,1),1),~f(1:end,1:end)] & [f(1:end,1:end),zeros(size(f,1),1)];
    [i,j] = find(img==1,1);
    startingPoint = [i,j]
    fsteps = {'NW','N','NE';'W','','E';'SW','S','SE'};
    validDirections = img(i-1:i+1,j-1:j+1);
    validDirections(2,2) = 0;
    
    [d1,d2] = find(validDirections==1,1);
    fstep = fsteps{d1,d2};

    fstep = 'N';
    
    B = bwtraceboundary(f,startingPoint,fstep);
end