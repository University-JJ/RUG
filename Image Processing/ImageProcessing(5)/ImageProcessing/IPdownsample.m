function [image] = IPdownsample(inputImage, factor)
image=zeros(floor(size(inputImage,1)/factor),floor(size(inputImage,2)/factor));
row=0;
for i=factor:factor:size(inputImage,1)
    row=row+1;
    column=1;
    for j=1:factor:size(inputImage,2)
        image(row,column)=inputImage(i,j);
        column=column+1;
    end
end
end