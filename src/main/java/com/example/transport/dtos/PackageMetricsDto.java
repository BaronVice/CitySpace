package com.example.transport.dtos;

public record PackageMetricsDto(
        String time,
        double lon,
        double lat,
        int speed
) {
}
