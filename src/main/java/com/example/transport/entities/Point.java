package com.example.transport.entities;


import lombok.Getter;
import lombok.Setter;

import java.util.Locale;
import java.util.Objects;

@Getter
@Setter
public class Point {
    private String lat;
    private String lon;

    public Point(double lon, double lat){
        this.lat = String.format(Locale.US, "%.4f", lat);
        this.lon = String.format(Locale.US, "%.4f", lon);
    }

    public PointDouble toPointDouble(){
        return new PointDouble(
                Double.parseDouble(lon),
                Double.parseDouble(lat)
        );
    }

    public double getLonDouble(){
        return Double.parseDouble(lon);
    }

    public double getLatDouble(){
        return Double.parseDouble(lat);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point point)) return false;
        return getLat().equals(point.getLat()) && getLon().equals(point.getLon());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLat(), getLon());
    }

    @Override
    public String toString() {
        return String.format("Point{ %s, %s }", lat, lon);
    }
}
