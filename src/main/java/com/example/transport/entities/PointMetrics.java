package com.example.transport.entities;

import com.example.transport.dtos.TransportPackageDto;

public class PointMetrics {
    long totalRequests;
    double avgSpeed;

    public PointMetrics(TransportPackageDto packageDto){
        totalRequests = 0;
        avgSpeed = 0;
    }

    public void updateMetrics(TransportPackageDto metrics){
        totalRequests++;
        updateAvgSpeed(metrics.speed_kmh());
    }

    private void updateAvgSpeed(double speed) {
        avgSpeed = avgSpeed * ( (double)(totalRequests-1) / totalRequests ) + speed / totalRequests;
    }
}
