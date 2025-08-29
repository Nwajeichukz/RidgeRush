package com.RidgeRush.repository;

import com.RidgeRush.entity.RaceResult;
import com.RidgeRush.entity.Rider;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RaceResultRepository extends JpaRepository<RaceResult,String> {
    @Query("SELECT CONCAT(rr.rider.firstName, ' ', rr.rider.lastName) " +
            "FROM RaceResult rr " +
            "WHERE rr.race.id = :raceId AND rr.didNotFinish = false " +
            "ORDER BY rr.totalTimeSeconds ASC")
    List<String> findTop3FastestRiders(@Param("raceId") String raceId, Pageable pageable);


    @Query("SELECT CONCAT(rr.rider.firstName, ' ', rr.rider.lastName) " +
            "FROM RaceResult rr " +
            "WHERE rr.race.id = :raceId AND rr.didNotFinish = true")
    List<String> findRiderNamesWhoDidNotFinish(@Param("raceId") String raceId);
}
