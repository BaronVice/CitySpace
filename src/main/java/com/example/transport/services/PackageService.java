package com.example.transport.services;

import com.example.transport.dtos.PackageDto;
import com.example.transport.entities.Point;
import com.example.transport.entities.PointMetrics;
import com.example.transport.mappers.PackageMapper;
import com.example.transport.repositories.PackageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class PackageService {
    @Qualifier("routeToPoints")
    private final Map<String, Map<Point, PointMetrics>> routeToPoints;
    @Qualifier("mapToPoints")
    private final Map<Point, PointMetrics> mapToPoints;
    private final PackageRepository packageRepository;
    private final PackageMapper packageMapper;

    public void handlePackages(List<PackageDto> packageDtoList){
        savePackages(packageDtoList);
        for(PackageDto packageDto: packageDtoList){
            addPackage(packageDto);
        }
    }

    public void handlePackage(PackageDto packageDto) {
        savePackage(packageDto);
        addPackage(packageDto);
    }

    private void addPackage(PackageDto packageDto) {
        String route = String.valueOf(packageDto.heading());
        Point point = new Point(packageDto.lon(), packageDto.lat());

        addIfAbsent(route, point, packageDto);
        updatePointMetrics(route, point, packageDto);
    }

    @Transactional
    @Async
    public void savePackage(PackageDto packageDto) {
        packageRepository.save(
                packageMapper.toPackage(packageDto)
        );
    }

    @Transactional
    @Async
    public void savePackages(List<PackageDto> packageDtoList){
        packageRepository.saveAllAndFlush(
                packageMapper.toPackageList(packageDtoList)
        );
    }

    private void updatePointMetrics(String route, Point point, PackageDto packageDto) {
        // Update on track
        routeToPoints.get(route).get(point).updateMetrics(packageDto);
        // Update on map
        mapToPoints.get(point).updateMetrics(packageDto);
    }

    private void addIfAbsent(String route, Point point, PackageDto packageDto) {
        routeToPoints.putIfAbsent(route, new ConcurrentHashMap<>());
        routeToPoints.get(route).putIfAbsent(
                point,
                new PointMetrics(packageDto)
        );

        mapToPoints.putIfAbsent(
                point,
                new PointMetrics(packageDto)
        );
    }
}
