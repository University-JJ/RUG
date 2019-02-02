function [image] = IPUpsample(inputImage, factor)
    image=zeros(size(inputImage,1)*factor,size(inputImage,2)*factor);
    row=0;
    for i=1:1:size(inputImage,1)
        row=row+factor;
        column=1;
        for j=1:1:size(inputImage,2)
            image(row,column)=inputImage(i,j);
            column=column+factor;
        end
    end
end