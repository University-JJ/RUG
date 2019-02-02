function [ipHist] = IPhisteq(inputImage)
    ipHist=IPhistogram(inputImage);
    total=sum(ipHist);
    ipHist=ipHist/total;
    S=zeros(256,1);
    for i=1:size(ipHist,2)
       S(i)=round((256-1)*sum(ipHist(1:i)));
    end
    figure(2);
    plot(S);
    xlim([0,256]);
    ylim([0,256]);
    
    P=zeros(256,1);
    for j=1:size(S)
        P(S(j)+1)=P(S(j)+1)+1;
    end

    figure(3);
    bar(P);
    xlim([0,256]);
end