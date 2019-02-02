%this functions calculates the sigmoid
function [output] = sigmoid(x)
    % Define the sigmoid function here
    [output] = 1.0 ./ (1.0 + exp(-x));    
end

