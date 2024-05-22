package com.example.transport.entities;

import com.example.transport.dtos.PackageDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PointMetrics {
    private long totalRequests;
    private double avgSpeed;

    public PointMetrics(PackageDto packageDto){
        totalRequests = 0;
        avgSpeed = 0;
    }

    public void updateMetrics(PackageDto metrics){
        totalRequests++;
        updateAvgSpeed(metrics.speed());
    }

    private void updateAvgSpeed(double speed) {
        avgSpeed = avgSpeed * ( (double)(totalRequests-1) / totalRequests ) + speed / totalRequests;
    }
}
