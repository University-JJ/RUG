clc;                                  % clear the command window
close all;                            % close open figure windows      
clear all;                            % remove items from workspace





imname='images/lincoln';                       % name of the input image
inputfile = [imname,'.tif'];          
f=imread(inputfile);                  % read input image
M = size(f,1);
N = size(f,2);
x=im2double(f);			      % convert to double


figure(16)
g = IPcontour(f);
colormap(gray(256));
imagesc(f);
hold on
scatter(g(:,2),g(:,1));


  g = IPcontour(f);
for p=2:50:502
    figure(17+p);
    s = IPfourierdescr(g,ceil(size(g,1)/p));
    scatter(s(:,1),s(:,2))
    title("p="+int2str(p));
end




return


% Write output to file
outputfile = [imname, '_sigma=' num2str(sigma), '.png'];
imwrite(g, outputfile);
fprintf('\nFiltered image saved in file %s\n', outputfile);

% Compute error between original and reconstruction
x=im2double(f);
error=sum(sum(abs(g-x)))/(M*N);
fprintf('\nerror=%f\n',error);

% Write output to file
dif_file = [imname,'_dif','_sigma=',num2str(sigma),'.png'];
dif_im=abs(g-x);
imwrite(dif_im, dif_file);
fprintf('\nDifference image has been saved in file %s\n', dif_file);

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
imagesc(g);
axis equal;
axis tight;
title('OUTPUT IMAGE')
hold on;

% Plot the difference image
dif_fig=im2uint8(dif_im);
  
subplot(133);
colormap(gray(256));
imagesc(dif_fig);
axis equal;
axis tight;
title('DIFFERENCE IMAGE')

% Write current figure to file
all_file = [imname,'_all','_sigma=',num2str(sigma),'.png'];
saveas(gcf,all_file);

fprintf('\nComplete image has been saved in file %s\n', all_file);

