function [image] = IPZoom(inputImage, factor)
image=zeros(size(inputImage,1)*factor,size(inputImage,2)*factor);
row=0;
for i=1:1:size(inputImage,1)
    row=row+factor;
    column=1;
    for j=1:1:size(inputImage,2)
        for k=0:factor-1
            for l=0:factor-1
                image(row-k,column+l)=inputImage(i,j);
            end
        end
        column=column+factor;
    end
end
end