package com.example.transport.dtos;

import java.util.List;

public record BetterRouteDto(
        String route,
        List<List<Double>> one,
        List<List<Double>> two
) {
}
