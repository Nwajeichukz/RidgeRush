package com.RidgeRush.repository;

import com.RidgeRush.entity.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RidersRepository extends JpaRepository<Rider, String> {

    boolean existsByEmail(String email);


    @Query("SELECT CONCAT(r.firstName, ' ', r.lastName) " +
            "FROM Rider r LEFT JOIN RaceResult rr " +
            "ON r.id = rr.rider.id AND rr.race.id = :raceId " +
            "WHERE rr.id IS NULL")
    List<String> findRiderNamesWhoDidNotParticipate(@Param("raceId") String raceId);

}
