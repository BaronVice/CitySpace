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
            @PathVariable String num
    ){
        return ResponseEntity.ok(
                metricsService.getPackageStatsByNum(num)
        );
    }

    @GetMapping
    public ResponseEntity<?> getMetricsByGap(
            @RequestParam(name = "gap", defaultValue = "0") int minutes
    ){
        return ResponseEntity.ok(
                metricsService.getPackageStatsGap(minutes)
        );
    }
}
