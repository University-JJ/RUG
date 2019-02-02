function im = IPerode(img,mask)

nRows = size(img,1);
nCols = size(img,2);

a = [img(2:nRows,:);ones(1,nCols)];
R{1,1} = [a(:,2:nCols),ones(nCols,1)];
R{1,2} = a;
R{1,3} = [ones(nRows,1), a(:,1:nCols-1)];

R{2,1} = [img(:,2:nCols),ones(nCols,1)];
R{2,2} = img;
R{2,3} = [ones(nRows,1), img(:,1:nCols-1)];

b = [ones(1,nCols);img(1:nRows-1,:)];
R{3,1} = [b(:,2:nCols),ones(nCols,1)];
R{3,2} = b;
R{3,3} = [ones(nRows,1), b(:,1:nCols-1)];

im = ones(nRows,nCols);

for i = 1:3
    for j = 1:3
        if mask(3-i+1,3-j+1)
           im = im & R{i,j};
        end
    end
end

end