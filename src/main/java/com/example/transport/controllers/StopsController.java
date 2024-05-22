package com.example.transport.controllers;

import com.example.transport.dtos.StopDto;
import com.example.transport.dtos.StopsDto;
import com.example.transport.services.StopsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/stops")
@RequiredArgsConstructor
public class StopsController {
    private final StopsService stopsService;

    @PostMapping
    public ResponseEntity<?> addStops(
            @RequestBody StopsDto stopsDto
    ){
        stopsService.addStops(stopsDto.stopDtos());
        return ResponseEntity.ok().build();
    }
}
