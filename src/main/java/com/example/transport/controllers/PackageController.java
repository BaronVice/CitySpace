package com.example.transport.controllers;

import com.example.transport.dtos.TransportPackageDto;
import com.example.transport.services.PackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("transport/package")
@RequiredArgsConstructor
public class PackageController {
    private final PackageService packageService;

    @PostMapping
    public ResponseEntity<?> receivePackage(
            @RequestBody TransportPackageDto packageDto
    ){
        packageService.handlePackage(packageDto);

        return ResponseEntity.ok().build();
    }
}
