package com.example.transport.services;

import com.example.transport.entities.Point;
import com.example.transport.entities.PointMetrics;
import com.example.transport.repositories.PackageRepository;
import com.example.transport.repositories.projections.PackageStats;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MetricsService {
    @Qualifier("routeToPoints")
    private final Map<String, Map<Point, PointMetrics>> routeToPoints;
    @Qualifier("mapToPoints")
    private final Map<Point, PointMetrics> mapToPoints;
    private final PackageRepository packageRepository;

    public List<PackageStats> getPackageStatsByNum(int num) {
        return packageRepository.getPackageStatsByHeading(num);
    }

    public List<PackageStats> getPackageStatsGap(int minutes) {
        String timestamp = getTime(minutes);
        return packageRepository.getPackageStatsByHeading(timestamp);
    }

    private String getTime(int minutes){
        return LocalDateTime.ofInstant(Instant.now().minus(minutes, ChronoUnit.MINUTES), ZoneId.of("Europe/Moscow"))
                .toString().split("T")[1].split("\\.")[0];
    }

    public List<PackageStats> getPackageStats() {
        return packageRepository.getAllBy();
    }
}
