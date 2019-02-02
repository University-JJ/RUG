f = imread('images/dipxetext.tif');
f = im2double(f);
F = fft2(f);
M = size(f,1);
N = size(f,2);
G = zeros(M,N);
k = 4.5;

for u=1:M
    for v=1:N
        hsmooth = 1/5*(1+2*cos(2*pi*u/M)+2*cos(2*pi*v/N));
        G(u,v) = (k+1-k*hsmooth)*F(u,v);
    end
end

imwrite(ifft2(G),'dipxetext3.png');