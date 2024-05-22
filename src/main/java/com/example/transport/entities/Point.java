package com.example.transport.entities;


import lombok.Getter;
import lombok.Setter;

import java.util.Locale;
import java.util.Objects;

@Getter
@Setter
public class Point {
    private String latitude;
    private String longitude;

    public Point(double longitude, double latitude){
        this.latitude = String.format(Locale.US, "%.6f", latitude);
        this.longitude = String.format(Locale.US, "%.6f", longitude);
    }

    public PointDouble toPointDouble(){
        return new PointDouble(
                Double.parseDouble(longitude),
                Double.parseDouble(latitude)
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point point)) return false;
        return getLatitude().equals(point.getLatitude()) && getLongitude().equals(point.getLongitude());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLatitude(), getLongitude());
    }

    @Override
    public String toString() {
        return String.format("Point{ %s, %s }", latitude, longitude);
    }
}
