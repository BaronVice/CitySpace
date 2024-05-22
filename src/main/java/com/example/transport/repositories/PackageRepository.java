package com.example.transport.repositories;

import com.example.transport.entities.db.Package;
import com.example.transport.repositories.projections.PackageStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackageRepository extends JpaRepository<Package, Integer> {
    String GET_PACKAGE_STATS_BY_HEADING =
            "select timestamp, lon, lat, speed from package where heading = :heading";

    String GET_PACKAGE_STATS_BY_TIME =
            "select timestamp, lon, lat, speed from package where timestamp <= :timestamp";

    @Query(value = GET_PACKAGE_STATS_BY_HEADING, nativeQuery = true)
    List<PackageStats> getPackageStatsByHeading(@Param("heading") int heading);

    @Query(value = GET_PACKAGE_STATS_BY_TIME, nativeQuery = true)
    List<PackageStats> getPackageStatsByHeading(@Param("timestamp") String timestamp);

    List<PackageStats> getAllBy();
}
