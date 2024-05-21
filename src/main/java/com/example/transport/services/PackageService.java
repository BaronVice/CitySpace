package com.example.transport.services;

import com.example.transport.dtos.TransportPackageDto;
import com.example.transport.entities.Point;
import com.example.transport.entities.PointMetrics;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class PackageService {
    // Track and its points
    private final Map<String, Map<Point, PointMetrics>> trackNumberToPoints;
    // Full map and its points
    private final Map<Point, PointMetrics> mapToPoints;

    public void handlePackage(TransportPackageDto packageDto) {
        String trackNumber = packageDto.trackNumber();
        Point point = new Point(packageDto.latitude(), packageDto.longitude());

        addIfAbsent(trackNumber, point, packageDto);
        updatePointMetrics(trackNumber, point, packageDto);
    }

    private void updatePointMetrics(String trackNumber, Point point, TransportPackageDto packageDto) {
        // Update on track
        trackNumberToPoints.get(trackNumber).get(point).updateMetrics(packageDto);
        // Update on map
        mapToPoints.get(point).updateMetrics(packageDto);
    }

    private void addIfAbsent(String trackNumber, Point point, TransportPackageDto packageDto) {
        trackNumberToPoints.putIfAbsent(trackNumber, new HashMap<>());
        trackNumberToPoints.get(trackNumber).putIfAbsent(
                point,
                new PointMetrics(packageDto)
        );

        mapToPoints.putIfAbsent(
                point,
                new PointMetrics(packageDto)
        );
    }
}
