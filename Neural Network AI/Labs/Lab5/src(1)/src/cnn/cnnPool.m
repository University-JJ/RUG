function pooledFeatures = cnnPool(poolDim, convolvedFeatures)
%cnnPool Pools the given convolved features
%
% Parameters:
%  poolDim - dimension of pooling region
%  convolvedFeatures - convolved features to pool (as given by cnnConvolve)
%                      convolvedFeatures(imageRow, imageCol, featureNum, imageNum)
%
% Returns:
%  pooledFeatures - matrix of pooled features in the form
%                   pooledFeatures(poolRow, poolCol, featureNum, imageNum)
%     

numImages = size(convolvedFeatures, 4);
numFilters = size(convolvedFeatures, 3);
convolvedDim = size(convolvedFeatures, 1);

pooledFeatures = zeros(convolvedDim / poolDim, ...
        convolvedDim / poolDim, numFilters, numImages);

% Instructions:
%   Now pool the convolved features in regions of poolDim x poolDim,
%   to obtain the 
%   (convolvedDim/poolDim) x (convolvedDim/poolDim) x numFeatures x numImages 
%   matrix pooledFeatures, such that
%   pooledFeatures(poolRow, poolCol, featureNum, imageNum) is the 
%   value of the featureNum feature for the imageNum image pooled over the
%   corresponding (poolRow, poolCol) pooling region. 
%   
%   Use mean pooling here.

%%% Add code here

for i= 1:numImages
  for j = 1:numFilters
    for k=1:(convolvedDim/poolDim) %We've done this because PooledOutputDim = (convolvedDim/poolDim)
      for l=1:(convolvedDim/poolDim)
        %In order to compute pooledFeatures, we've divided the convolvedFeatures in 
        %(convolvedDim/poolDim)^2 bolcks.
        %It contains the mean of the values in these blocks.
        pooledFeatures(k,l,j,i) = mean2(convolvedFeatures((poolDim*(k-1)+1):poolDim*k, (poolDim*(l-1)+1):poolDim*l,j,i));
      end
    end
  end
end

end

