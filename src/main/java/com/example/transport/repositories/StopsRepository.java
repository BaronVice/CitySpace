package com.example.transport.repositories;

import com.example.transport.entities.db.Stop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StopsRepository extends JpaRepository<Stop, Integer> {
    Optional<Stop> findByName(String name);
}
