package com.example.transport.repositories.projections;

import org.springframework.beans.factory.annotation.Value;

public interface PackageStats {
    @Value("#{target.timestamp}")
    String getTimestamp();
    @Value("#{target.lon}")
    double getLon();
    @Value("#{target.lat}")
    double getLat();
    @Value("#{target.speed}")
    int getSpeed();
}
