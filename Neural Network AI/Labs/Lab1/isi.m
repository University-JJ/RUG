function isi_result = isi(spiketimes)
    if(length(spiketimes)>1)
        isi_result = diff(spiketimes);
    else
        isi_result = [];
end