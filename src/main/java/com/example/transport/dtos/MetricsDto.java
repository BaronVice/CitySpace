package com.example.transport.dtos;

public record MetricsDto(
        String time,
        double lon,
        double lat,
        double speed
) {
}
