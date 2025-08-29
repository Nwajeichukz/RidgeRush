package com.RidgeRush.service.impl;

import com.RidgeRush.dto.request.*;
import com.RidgeRush.dto.response.AppResponse;
import com.RidgeRush.dto.response.RaceResponse;
import com.RidgeRush.entity.Race;
import com.RidgeRush.exception.ApiException;
import com.RidgeRush.repository.RaceRepository;
import com.RidgeRush.service.RaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RaceServiceImpl implements RaceService {
    private final RaceRepository raceRepository;

    @Override
    public AppResponse<String> createRace(RaceCreationDto creationDto) {
        boolean check = raceRepository.existsByName(creationDto.getName());

        if (check) throw new ApiException("race already exists");

        Race race = new Race();
        race.setName(creationDto.getName());
        race.setLocation(creationDto.getLocation());
        race.setStartTime(creationDto.getStartTime());
        Race raceSaved = raceRepository.save(race);

        return new AppResponse<>("successful", raceSaved.getName());
    }

    public AppResponse<RaceResponse> getRaceById(String raceId) {
        Race race = raceRepository.findById(raceId)
                .orElseThrow(() -> new ApiException("Race not found"));
        return new AppResponse<>("successful", new RaceResponse(race));
    }

    @Override
    public AppResponse<String> updateRace(String raceId, RaceUpdateDto updateDto) {
        Race race = raceRepository.findById(raceId)
                .orElseThrow(() -> new ApiException("Race not found"));

        if (updateDto.getName() != null) race.setName(updateDto.getName());
        if (updateDto.getLocation() != null) race.setLocation(updateDto.getLocation());
        if (updateDto.getStartTime() != null) race.setStartTime(updateDto.getStartTime());

        raceRepository.save(race);
        return new AppResponse<>("successful", "Race updated successfully");
    }

}