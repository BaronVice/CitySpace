package com.example.transport.dtos;

public record PackageDto(
    String uniqueid,
    String timestamp,
    int bus_id,
    int heading,
    int speed,
    double lon,
    double lat,
    int direction,
    String gosnum,
    String bortnum,
    int probeg
){

}
