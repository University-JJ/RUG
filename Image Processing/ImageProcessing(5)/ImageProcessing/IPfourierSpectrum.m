img = imread('images/characters.tif');
doubledImage = im2double(img);
fft2Res = fft2(doubledImage);
fourierSpectrum = abs(fftshift(fft2Res));
imwrite(img,'characters.png');
imwrite(fourierSpectrum/255,'characters2.png');

% Average value
avg = fft2Res(1,1)/(size(img,1)*size(img,2))*255
% Conventional determination of average value
avg2 = mean(mean(img))