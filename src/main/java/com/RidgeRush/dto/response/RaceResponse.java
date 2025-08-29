package com.RidgeRush.dto.response;

import com.RidgeRush.entity.Race;
import com.RidgeRush.entity.RaceResult;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class RaceResponse {
    private String id;
    private String name;
    private String location;
    private String startTime;
    private List<String> raceResults;

    public RaceResponse(Race race) {
        this.id = race.getId();
        this.name = race.getName();
        this.location = race.getLocation();
        this.startTime = race.getStartTime();
        this.raceResults = race.getRaceResults()
                .stream()
                .map(RaceResult::getId)
                .collect(Collectors.toList());
    }
}
