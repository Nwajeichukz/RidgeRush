package com.RidgeRush.service;

import com.RidgeRush.dto.request.BulkRaceResultCreationsDto;
import com.RidgeRush.dto.request.RaceResultCreationsDto;
import com.RidgeRush.dto.request.RaceResultUpdateDto;
import com.RidgeRush.dto.response.AppResponse;
import com.RidgeRush.dto.response.RaceResultResponse;

import java.util.List;

public interface RaceResultService {
    AppResponse<RaceResultResponse> raceResult(RaceResultCreationsDto raceResult);

    AppResponse<String> raceResultsBulk(BulkRaceResultCreationsDto bulkRaceResult);

    AppResponse<RaceResultResponse> getRaceResultById(String id);

    AppResponse<List<RaceResultResponse>> getAllRaceResults();

    AppResponse<RaceResultResponse> updateRaceResult(String id, RaceResultUpdateDto updateDto);
}
