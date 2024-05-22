package com.example.transport.services;

import com.example.transport.entities.Point;
import com.example.transport.entities.PointMetrics;
import com.example.transport.exceptions.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MapService {
    // Track and its points
    @Qualifier("routeToPoints")
    private final Map<String, Map<Point, PointMetrics>> routeToPoints;
    // Full map and its points
    @Qualifier("mapToPoints")
    private final Map<Point, PointMetrics> mapToPoints;

    public Map<Point, PointMetrics> getMap(){
        return mapToPoints;
    }

    public Map<Point, PointMetrics> getMap(String route){
        Map<Point, PointMetrics> map = routeToPoints.get(route);
        if (map == null)
            throw new ApplicationException(
                    "Route not found",
                    HttpStatus.NOT_FOUND
            );

        return map;
    }
}
