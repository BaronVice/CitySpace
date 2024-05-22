package com.example.transport.controllers;

import com.example.transport.services.MetricsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/metrics")
@RequiredArgsConstructor
public class MetricsController {
    private final MetricsService metricsService;

    @GetMapping("/{num}")
    public ResponseEntity<?> getMetricsByNum(
            @PathVariable int num
    ){
        return ResponseEntity.ok(
                metricsService.getPackageStatsByNum(num)
        );
    }

    @GetMapping("/filter")
    public ResponseEntity<?> getMetricsByGap(
            @RequestParam(name = "gap", defaultValue = "30") int minutes
    ){
        return ResponseEntity.ok(
                metricsService.getPackageStatsGap(minutes)
        );
    }

    @GetMapping()
    public ResponseEntity<?> getMetrics(){
        return ResponseEntity.ok(
                metricsService.getPackageStats()
        );
    }
}
