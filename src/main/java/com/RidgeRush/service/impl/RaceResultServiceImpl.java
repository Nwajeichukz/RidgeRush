package com.RidgeRush.service.impl;

import com.RidgeRush.dto.request.BulkRaceResultCreationsDto;
import com.RidgeRush.dto.request.RaceResultCreationsDto;
import com.RidgeRush.dto.request.RaceResultUpdateDto;
import com.RidgeRush.dto.request.RiderResultDto;
import com.RidgeRush.dto.response.AppResponse;
import com.RidgeRush.dto.response.RaceResultResponse;
import com.RidgeRush.entity.Race;
import com.RidgeRush.entity.RaceResult;
import com.RidgeRush.entity.Rider;
import com.RidgeRush.exception.ApiException;
import com.RidgeRush.repository.RaceRepository;
import com.RidgeRush.repository.RaceResultRepository;
import com.RidgeRush.repository.RidersRepository;
import com.RidgeRush.service.RaceResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RaceResultServiceImpl implements RaceResultService {
    private final RaceResultRepository raceResultRepository;

    private final RidersRepository ridersRepository;

    private final RaceRepository raceRepository;

    @Override
    public AppResponse<RaceResultResponse> raceResult(RaceResultCreationsDto raceResult) {
        Rider rider = ridersRepository.findById(raceResult.getRiderId())
                .orElseThrow(() -> new ApiException("Rider Not Found"));

        Race race = raceRepository.findById(raceResult.getRiderId())
                .orElseThrow(() -> new ApiException("Race Not Found"));

        RaceResult result = new RaceResult();
        result.setRider(rider);
        result.setRace(race);
        result.setPosition(raceResult.getPosition());
        result.setTotalTimeSeconds(raceResult.getTotalTimeSeconds());
        result.setFinishTime(raceResult.getFinishTime());

        //TODO: WHO DOSENT finish
        if (raceResult.getFinishTime() == null || raceResult.getFinishTime().isBlank())
            result.setDidNotFinish(true);

        RaceResult raceResultSaved = raceResultRepository.save(result);

        return new AppResponse<>("successful", new RaceResultResponse(raceResultSaved));
    }

    @Override
    public AppResponse<String> raceResultsBulk(BulkRaceResultCreationsDto bulkDto) {
        Race race = raceRepository.findById(bulkDto.getRaceId())
                .orElseThrow(() -> new ApiException("Race Not Found"));

        List<RaceResult> resultsToSave = new ArrayList<>();

        for (RiderResultDto riderDto : bulkDto.getRiders()) {
            Rider rider = ridersRepository.findById(riderDto.getRiderId())
                    .orElseThrow(() -> new ApiException("Rider Not Found: " + riderDto.getRiderId()));

            RaceResult result = new RaceResult();
            result.setRace(race);
            result.setRider(rider);
            result.setPosition(riderDto.getPosition());
            result.setTotalTimeSeconds(riderDto.getTotalTimeSeconds());
            result.setFinishTime(riderDto.getFinishTime());

            if (riderDto.getFinishTime() == null || riderDto.getFinishTime().isBlank()) {
                result.setDidNotFinish(true);
            }

            resultsToSave.add(result);
        }

        List<RaceResult> savedResults = raceResultRepository.saveAll(resultsToSave);

        return new AppResponse<>("successful", "Race with id of "
                + savedResults.get(0).getRace().getId() + " saved");
    }

    @Override
    public AppResponse<RaceResultResponse> getRaceResultById(String id) {
        RaceResult raceResult = raceResultRepository.findById(id)
                .orElseThrow(() -> new ApiException("RaceResult not found"));
        return new AppResponse<>("successful", new RaceResultResponse(raceResult));
    }

    @Override
    public AppResponse<List<RaceResultResponse>> getAllRaceResults() {
        List<RaceResultResponse> results = raceResultRepository.findAll()
                .stream()
                .map(RaceResultResponse::new)
                .collect(Collectors.toList());
        return new AppResponse<>("successful", results);
    }

    @Override
    public AppResponse<RaceResultResponse> updateRaceResult(String id, RaceResultUpdateDto updateDto) {
        RaceResult raceResult = raceResultRepository.findById(id)
                .orElseThrow(() -> new ApiException("RaceResult not found"));

        if (updateDto.getFinishTime() != null) {
            raceResult.setFinishTime(updateDto.getFinishTime());
        }

        if (updateDto.getTotalTimeSeconds() != null) {
            raceResult.setTotalTimeSeconds(updateDto.getTotalTimeSeconds());
        }

        if (updateDto.getPosition() != null) {
            raceResult.setPosition(updateDto.getPosition());
        }

        if (updateDto.getDidNotFinish() != null) {
            raceResult.setDidNotFinish(updateDto.getDidNotFinish());
        }

        RaceResult updated = raceResultRepository.save(raceResult);
        return new AppResponse<>("updated successfully", new RaceResultResponse(updated));
    }

}