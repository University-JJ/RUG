for i = 1:3
    nstep = 100; %Number of timesteps to over
    Inoise = 0.1;
    IO = 1+Inoise*randn(1,nstep);%Input current in nA
    dt = 1;
    tau = [2 5 10];
    theta = 4; %threshold in mV 
    v = zeros(1, nstep);
    Rin = 5; %Input resistance in MOhm
    tspike = [];
    isi_result = [];
    t = (1:nstep)*dt;
    for n=2:nstep
        v(n) = v(n-1) + dt*(-v(n-1)/tau(i) + Rin*IO(n)/tau(i));
        if (v(n) > theta)
            v(n)=0;
            tspike = [tspike t(n)];
            
        end
    end
    figure(i);
    plot(t,v);
    xlabel('Time');ylabel('Voltage');
    title(['Tau: ', num2str(tau(i)), '   Theta: ', num2str(theta), '   Rin: ', num2str(Rin)]);
    figure(i+3);
    hist(isi(tspike), 1:100); xlabel('ISI/ms'); ylabel('Counts');
    title(['Tau: ', num2str(tau(i)), '   Theta: ', num2str(theta), '   Rin: ', num2str(Rin)]);
    hold on;
end
