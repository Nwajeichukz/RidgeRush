package com.RidgeRush.repository;

import com.RidgeRush.entity.Race;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RaceRepository extends JpaRepository<Race, String> {

    boolean existsByName(String name);
}
