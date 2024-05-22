package com.example.transport.services;

import com.example.transport.dtos.BetterRouteDto;
import com.example.transport.dtos.RouteDto;
import com.example.transport.entities.Point;
import com.example.transport.entities.PointDouble;
import com.example.transport.entities.PointMetrics;
import com.example.transport.entities.db.Route;
import com.example.transport.entities.db.Stop;
import com.example.transport.exceptions.ApplicationException;
import com.example.transport.mappers.RouteMapper;
import com.example.transport.repositories.RouteRepository;
import com.example.transport.repositories.StopsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RouteService {
    private final StopsRepository stopsRepository;
    private final RouteRepository routeRepository;
    private final RouteMapper routeMapper;

    @Qualifier("routeToPoints")
    private final Map<String, Map<Point, PointMetrics>> routeToPoints;
    // Full map and its points
    @Qualifier("mapToPoints")
    private final Map<Point, PointMetrics> mapToPoints;
    @Qualifier("routes")
    private final Map<String, List<List<Point>>> routes;

    public void addRoute(RouteDto routeDto){
        Route route = routeMapper.toRoute(routeDto);

        List<Stop> stops = new ArrayList<>();
        for(String stopString : routeDto.stops()){
            Stop stop = stopsRepository.findByName(stopString).orElseThrow(
                    () -> new ApplicationException(
                            String.format("Stop <%s> is not found", stopString),
                            HttpStatus.NOT_FOUND
                    )
            );
            stops.add(stop);
        }
        saveRoute(route, stops);
    }

    @Transactional
    @Async
    public void saveRoute(Route route, List<Stop> stops){
        routeRepository.save(route);
        route.setStops(stops);
        routeRepository.save(route);
    }

    public void addRoute(BetterRouteDto routeDto){
        List<Point> one = new ArrayList<>();
        List<Point> two = new ArrayList<>();

        for(List<Double> el : routeDto.one()){
            one.add(new Point(el.get(0), el.get(1)));
        }
        for(List<Double> el : routeDto.two()){
            two.add(new Point(el.get(0), el.get(1)));
        }

        routes.put(routeDto.route(), List.of(one, two));
    }

    public List<PointDouble> getRoute(String num, int dir) {
        if (!routes.containsKey(num)) {
            throw new ApplicationException(
                    "Route not found",
                    HttpStatus.NOT_FOUND
            );
        }
        if (dir != 0 && dir != 1) dir = 0;

        return routes.get(num).get(dir).stream().map(Point::toPointDouble).collect(Collectors.toList());
    }

    public List<String> getRouteNumbers() {
        return new ArrayList<>(routes.keySet());
    }
}
