package com.RidgeRush.service.impl;

import com.RidgeRush.dto.response.AppResponse;
import com.RidgeRush.dto.response.RaceResultResponse;
import com.RidgeRush.entity.Race;
import com.RidgeRush.entity.RaceResult;
import com.RidgeRush.entity.Rider;
import com.RidgeRush.exception.ApiException;
import com.RidgeRush.repository.RaceRepository;
import com.RidgeRush.repository.RaceResultRepository;
import com.RidgeRush.repository.RidersRepository;
import com.RidgeRush.service.ResultDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResultDashboardServiceImpl implements ResultDashboardService {
    private final RaceResultRepository raceResultRepository;

    private final RaceRepository raceRepository;

    private final RidersRepository ridersRepository;

    @Override
    public List<String> getTop3Riders(String raceId) {
        return raceResultRepository.findTop3FastestRiders(raceId, PageRequest.of(0, 3));

    }

    @Override
    public List<String> getRidersWhoDidNotFinish(String raceId) {
        return raceResultRepository.findRiderNamesWhoDidNotFinish(raceId);
    }

    @Override
    public List<String> getRidersWhoDidNotParticipate(String raceId) {
        return ridersRepository.findRiderNamesWhoDidNotParticipate(raceId);
    }


    @Override
    public AppResponse<String> getRaceWeather(String raceId) {
        Race race = raceRepository.findById(raceId)
                .orElseThrow(() -> new ApiException("Race not found"));

        //TODO: TAKE THE TIME AND LOCATION OF THE RACE

        //TODO: INETGRATE WEATHER API
        String weather = "Sunny with mild wind";

        return new AppResponse<>("Weather fetched successfully", weather);
    }

}
