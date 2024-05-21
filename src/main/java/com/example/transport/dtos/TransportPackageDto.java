package com.example.transport.dtos;

public record TransportPackageDto(
    int trackerId,
    String trackNumber,
    String stop,
    String next_stop,
    double latitude,
    double longitude,
    double speed_kmh
){

}
