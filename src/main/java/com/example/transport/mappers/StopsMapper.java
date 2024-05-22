package com.example.transport.mappers;

import com.example.transport.dtos.StopDto;
import com.example.transport.entities.db.Stop;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface StopsMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lat", source = "lat", qualifiedByName = "transformCords")
    @Mapping(target = "lon", source = "lon", qualifiedByName = "transformCords")
    Stop toStop(StopDto stopDto);

    @Named("transformCords")
    static String transformCords(double cord){
        return String.format("%.4f", cord);
    }
}
