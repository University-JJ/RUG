f=imread('images/vase.tif');
f = im2double(f);
mask = [1,1,1;1,1,1;1,1,1];

figure(1)
img = IPgdilate(f, mask);
img2 = IPgerode(img, mask);
colormap(gray(256));
imagesc(img2);