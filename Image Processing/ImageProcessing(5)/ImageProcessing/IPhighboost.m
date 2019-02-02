f = imread('images/dipxetext.tif');
f = im2double(f);
hsmooth = [0,1,0;1,1,1;0,1,0]*1/5;

fsmooth = IPfilter(f,hsmooth);

gmask = f(:,:) - fsmooth(:,:);
k = 4.5;
g = f + k * gmask;

imwrite(f,'dipxetext.png');
imwrite(g,'dipxetext2.png');
