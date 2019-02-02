function s = IPfourierdescr(B,P)
    s1 = B(:,1) + B(:,2)*1j;
    
    K = size(s1,1);
  
    a = zeros(K,1);
    for u=0:K-1
        for k=0:K-1
            a(u+1,1) = a(u+1,1) + s1(k+1,1)*exp((-2j*pi*u*k)/K);
        end
    end
    
    s = zeros(K,1);
    for k=0:K-1
        for u=0:P
            s(k+1,1) = s(k+1,1) + a(u+1,1)*exp((2j*pi*u*k)/K);
        end
        for u=K-(P+1):K-1
            s(k+1,1) = s(k+1,1) + a(u+1,1)*exp((2j*pi*u*k)/K);
        end
    end
    s = s./K;
    
    s = [real(s),imag(s)];
end