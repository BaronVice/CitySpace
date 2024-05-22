package com.example.transport.controllers;

import com.example.transport.dtos.PackageDto;
import com.example.transport.services.PackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/package")
@RequiredArgsConstructor
public class PackageController {
    private final PackageService packageService;

    @PostMapping
    public ResponseEntity<?> sendPackage(
            @RequestBody PackageDto packageDto
    ){
        packageService.handlePackage(packageDto);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/all")
    public ResponseEntity<?> sendPackages(
            @RequestBody List<PackageDto> packageDtos
    ){
        packageService.handlePackages(packageDtos);

        return ResponseEntity.ok().build();
    }
}
