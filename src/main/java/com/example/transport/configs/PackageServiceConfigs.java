package com.example.transport.configs;

import com.example.transport.entities.Point;
import com.example.transport.entities.PointMetrics;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class PackageServiceConfigs {
    @Bean
    public Map<String, Map<Point, PointMetrics>> trackNumberToPoints(){
        return new HashMap<>();
    }

    public Map<Point, PointMetrics> mapToPoints(){
        return new HashMap<>();
    }
}
