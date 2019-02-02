clc;                                  % clear the command window
close all;                            % close open figure windows
clear all;                            % remove items from workspace

imname='plant';                       % name of the input image
sigma = 1.0;                          % sigma of gaussian filter
J = 3;                                % J to be used by IPpyr_decomp
K = 0;                                % K to be used by IPpyr_decomp partiality

inputfile = ['images/',imname,'.tif'];
f=imread(inputfile);                  % read input image
M = size(f,1);
N = size(f,2);

x=im2double(f);                       % convert to double
g=IPpyr_decomp(f,J,sigma);
g2=IPpyr_recon(g,J,K,sigma); 	          % Laplacian pyramid: Reconstruction

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

mae = mean(mean(abs(x-g2)));          % Mean absolute error between
% input image and reconstruction.

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

d = abs(im2uint8(g2)-f);              % compute difference image

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Plot images

% Plot the input image
figure;
subplot(131);
colormap(gray(256));
imagesc(f);
axis equal;
axis tight;
title('INPUT IMAGE')
hold on;

% Plot the output image
subplot(132);
colormap(gray(256));
imagesc(g2);
axis equal;
axis tight;
title('OUTPUT IMAGE')
hold on;

% Plot the difference image
subplot(133);
colormap(gray(256));
imagesc(d);
axis equal;
axis tight;
title('DIFFERENCE IMAGE')
hold on;

imageName = [imname,'_recon_error=',num2str(mae),'_J=',num2str(J),...
    '_sigma=',num2str(sigma),'.png'];

saveas(gcf,imageName);

