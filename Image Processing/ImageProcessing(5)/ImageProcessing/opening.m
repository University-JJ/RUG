f=imread('images/vase.tif');
f = im2double(f);
mask = [1,1,1;1,1,1;1,1,1];

figure(1)
img = IPgerode(f,mask);
img2 = IPgdilate(img, mask);
colormap(gray(256));
imagesc(img2);

