clear all;
close all;
clc;

f=imread('images/wirebondmask.tif'); 
mask = [0,1,0;1,1,1;0,1,0];

figure(1)
colormap(gray(256));
imagesc(f);

figure(2)
img = IPdilate(f,mask);
colormap(gray(256));
imagesc(img);

img2 = imdilate(f,mask);
min(min(img==img2))

figure(3)
img = IPerode(f,mask);
colormap(gray(256));
imagesc(img);

img2 = imerode(f,mask);
min(min(img==img2))

f=imread('images/vase.tif');
f = im2double(f);
mask = [1,1,1;1,1,1;1,1,1];

figure(4)
colormap(gray(256));
imagesc(f);

figure(5)
img = IPgdilate(f,mask);
colormap(gray(256));
imagesc(img);

img2 = imdilate(f,mask);
img2(img2==Inf) = 1;
img2(img2==-Inf) = 0;
min(min(img==img2))

figure(6)
img = IPgerode(f,mask);
colormap(gray(256));
imagesc(img);

img2 = imerode(f,mask);
img2(img2==Inf) = 1;
min(min(img==img2))


