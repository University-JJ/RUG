function im = IPgdilate(img,mask)

nRows = size(img,1);
nCols = size(img,2);

a = [img(2:nRows,:);zeros(1,nCols)];
R{1,1} = [a(:,2:nCols),zeros(nCols,1)];
R{1,2} = a;
R{1,3} = [zeros(nRows,1), a(:,1:nCols-1)];

R{2,1} = [img(:,2:nCols),zeros(nCols,1)];
R{2,2} = img;
R{2,3} = [zeros(nRows,1), img(:,1:nCols-1)];

b = [zeros(1,nCols);img(1:nRows-1,:)];
R{3,1} = [b(:,2:nCols),zeros(nCols,1)];
R{3,2} = b;
R{3,3} = [zeros(nRows,1), b(:,1:nCols-1)];

im = zeros(nRows,nCols);

for i = 1:3
    for j = 1:3
        if mask(i,j)
           im = max(im,R{i,j});
        end
    end
end

end