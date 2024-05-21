package com.example.transport.entities;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Point {
    private String latitude;
    private String longitude;

    public Point(double latitude, double longitude){
        this.latitude = String.format("%.4f", latitude);
        this.longitude = String.format("%.4f", longitude);
    }
}
