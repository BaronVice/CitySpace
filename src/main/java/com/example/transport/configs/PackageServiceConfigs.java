package com.example.transport.configs;

import com.example.transport.entities.Point;
import com.example.transport.entities.PointMetrics;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class PackageServiceConfigs {
    @Bean(name = "routeToPoints")
    public Map<String, Map<Point, PointMetrics>> routeToPoints(){
        return new ConcurrentHashMap<>();
    }

    @Bean(name = "mapToPoints")
    public Map<Point, PointMetrics> mapToPoints(){
        return new ConcurrentHashMap<>();
    }

    @Bean(name = "routes")
    public Map<String, List<List<Point>>> routes(){
        return new ConcurrentHashMap<>();
    }
}
