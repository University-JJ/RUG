%THIS FUNCTION HAS BEEN ADDED IN ORDER TO OBTAIN
%THE OUTPUT OF THE NEURON APPLYING THE SIGMOID 
%FUNCTION
function [output] = sigmoid(x)
    % Define the sigmoid function here
    [output] = 1.0 ./ (1.0 + exp(-x));    
end

