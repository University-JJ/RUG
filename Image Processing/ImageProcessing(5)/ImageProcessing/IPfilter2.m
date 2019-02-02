function [result] = IPfilter(inputImage,mask)
    result=zeros(size(inputImage,1),size(inputImage,2));
    for i=2:size(inputImage,1)-1
        for j=2:size(inputImage,2)-1
            result(i,j) = sum(sum(double(inputImage(i-1:i+1,j-1:j+1)).*mask));
        end
    end
end
