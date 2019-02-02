function [A] = IPhistogram(inputImage)
    A=zeros(1,256);
    for i=1:size(inputImage,1)
        for j=1:size(inputImage,2)
                A(1,inputImage(i,j)+1)=A(1,inputImage(i,j)+1)+1;
        end
    end
  
    bar(A)
    xlim([0,256]);
end