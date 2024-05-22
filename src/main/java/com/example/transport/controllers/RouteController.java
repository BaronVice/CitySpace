package com.example.transport.controllers;

import com.example.transport.dtos.BetterRouteDto;
import com.example.transport.dtos.RouteDto;
import com.example.transport.services.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/route")
@RequiredArgsConstructor
public class RouteController {
    private final RouteService routeService;

//    @PostMapping
//    public ResponseEntity<?> addRoute(
//            @RequestBody RouteDto routeDto
//    ){
//        routeService.addRoute(routeDto);
//
//        return ResponseEntity.ok().build();
//    }

    @PostMapping
    public ResponseEntity<?> addRoute(
            @RequestBody BetterRouteDto routeDto
    ){
        routeService.addRoute(routeDto);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{num}")
    public ResponseEntity<?> getRoute(
            @PathVariable String num,
            @RequestParam(name = "dir", defaultValue = "0") int dir
    ){
        return ResponseEntity.ok(
                routeService.getRoute(num, dir)
        );
    }

    @GetMapping
    public ResponseEntity<?> getRouteNumbers(){
        return ResponseEntity.ok(
                routeService.getRouteNumbers()
        );
    }
}
