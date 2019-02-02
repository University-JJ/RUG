function im = IPgerode(img,mask)

nRows = size(img,1);
nCols = size(img,2);
maxVal = max(max(img));

a = [img(2:nRows,:);ones(1,nCols)*maxVal];
R{1,1} = [a(:,2:nCols),ones(nCols,1)*maxVal];
R{1,2} = a;
R{1,3} = [ones(nRows,1)*maxVal, a(:,1:nCols-1)];

R{2,1} = [img(:,2:nCols),ones(nCols,1)*maxVal];
R{2,2} = img;
R{2,3} = [ones(nRows,1)*maxVal, img(:,1:nCols-1)];

b= [ones(1,nCols)*maxVal;img(1:nRows-1,:)];
R{3,1} = [b(:,2:nCols),ones(nCols,1)*maxVal];
R{3,2} = b;
R{3,3} = [ones(nRows,1)*maxVal, b(:,1:nCols-1)];

im = Inf(nRows,nCols);

for i = 1:3
    for j = 1:3
        if mask(3-i+1,3-j+1)
           im = min(im,R{i,j});
        end
    end
end

end