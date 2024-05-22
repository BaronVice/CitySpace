package com.example.transport.mappers;

import com.example.transport.dtos.RouteDto;
import com.example.transport.entities.db.Route;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RouteMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "stops", ignore = true)
    @Mapping(source = "name", target = "name")
    Route toRoute(RouteDto routeDto);
}
