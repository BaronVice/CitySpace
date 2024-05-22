package com.example.transport.services;

import com.example.transport.dtos.StopDto;
import com.example.transport.entities.db.Stop;
import com.example.transport.mappers.StopsMapper;
import com.example.transport.repositories.StopsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StopsService {
    private final StopsRepository stopsRepository;
    private final StopsMapper stopsMapper;

    @Async
    public void addStops(StopDto[] stopsDto){
        List<Stop> stops = Arrays.stream(stopsDto).map(stopsMapper::toStop).toList();
        stopsRepository.saveAllAndFlush(stops);
    }
}
