package com.example.transport.mappers;

import com.example.transport.dtos.PackageDto;
import com.example.transport.entities.db.Package;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PackageMapper {
    @Mapping(target = "id", ignore = true)
    Package toPackage(PackageDto packageDto);
    List<Package> toPackageList(List<PackageDto> packageDtoList);
}
