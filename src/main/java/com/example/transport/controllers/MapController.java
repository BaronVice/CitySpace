package com.example.transport.controllers;

import com.example.transport.services.MapService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/map")
@RequiredArgsConstructor
public class MapController {
    private final MapService mapService;

    @GetMapping
    public ResponseEntity<?> getMap(){
        return ResponseEntity.ok(
                mapService.getMap()
        );
    }

    @GetMapping("/{route}")
    public ResponseEntity<?> getMapByRoute(
            @PathVariable String route
    ){
        return ResponseEntity.ok(
                mapService.getMap(route)
        );
    }
}
