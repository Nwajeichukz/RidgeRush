package com.RidgeRush.service;

import com.RidgeRush.dto.request.RaceUpdateDto;
import com.RidgeRush.dto.response.AppResponse;
import com.RidgeRush.dto.request.RaceCreationDto;
import com.RidgeRush.dto.response.RaceResponse;
import com.RidgeRush.entity.Race;

public interface RaceService {
    AppResponse<String> createRace(RaceCreationDto creationDto);

    AppResponse<RaceResponse> getRaceById(String raceId);

    AppResponse<String> updateRace(String raceId, RaceUpdateDto updateDto);
}