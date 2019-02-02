function [g, blocks] = splitmerge(f, mindim, fun)
%SPLITMERGE Segment and image using a split-and-merge algorithm. 
%   [g, blocks] = splitmerge(f, mindim, @predicate) segments image f by using a
%   split-and-merge approach based on quadtree decomposition. mindim (a
%   positive integer power of 2) specifies the minimum dimension of the
%   quadtree regions (subimages) allowed. If necessary, the program pads
%   the input image with zeros to the nearest square size that is an
%   integer power of 2. This guarantees that the algorithm used in the
%   quadtree decomposition will be able to split the image down to blocks
%   of size 1-by-1. The result is cropped back to the original size of the
%   input image. In the output, g, each connected component is labelled
%   with a different integer. The other output, blocks, is an image of the
%   quadtree regions.
%
%   predicate is a function in the Matlab path provided by the user. Its
%   syntax is
%       flag = predicate(region) which must return TRUE if the pixels in
%       region satisfy the predicate defined by the code in the function;
%       otherwise, the value of flag must be FALSE.
%   Note that splitmerge is called with @predicate (a function pointer)
%  



% Pad image with zeros to guarantee that function qtdecomp will
% split regions down to size 1 by 1.
Q = 2^nextpow2(max(size(f)));
[M, N] = size(f);
f = padarray(f, [Q-M, Q-N], 'post');

% Perform splitting first
S = qtdecomp(f, @split_test, mindim, fun);

blocks = repmat(uint8(0),size(S));
for dim = [2048 1024 512 256 128 64 32 16 8 4 2 1];    
    numblocks = length(find(S==dim));    
    if (numblocks > 0)        
        values = repmat(uint8(255),[dim dim numblocks]);
        values(2:dim,2:dim,:) = 0;
        blocks = qtsetblk(blocks,S,dim,values);
    end
end
blocks(end,1:end) = 255;
blocks(1:end,end) = 255;
blocks = blocks(1:M, 1:N);

% Get the size of the largest block.
Lmax = full(max(S(:)));

g = zeros(size(f));
MARKER = zeros(size(f));

for K=1:Lmax
    [vals, r, c] = qtgetblk(f, S, K);
    if (~isempty(vals))
        for I=1:length(r)
            xlow = r(I); ylow = c(I);
            xhigh = xlow + K - 1; yhigh = ylow + K - 1;
            region = f(xlow:xhigh, ylow:yhigh);
            flag = feval(fun, region);
            if flag
                g(xlow:xhigh, ylow:yhigh) = 1;
                MARKER(xlow, ylow) = 1;
            end
        end
    end
end

% Obtain each connected component and label it with a different integer
g = bwlabel(imreconstruct(MARKER, g));

% Crop
g = g(1:M, 1:N);



%---------------
function v = split_test(B, mindim, fun)

k = size(B, 3);

v(1:k) = false;
for I=1:k
    quadregion = B(:, :, I);
    if (size(quadregion, 1) <= mindim)
        v(I) = false;
        continue
    end
    flag = feval(fun, quadregion);
    if (~flag)
        v(I) = true;
    end
end

