package com.RidgeRush.service;

import com.RidgeRush.dto.response.AppResponse;
import com.RidgeRush.dto.response.RaceResultResponse;

import java.util.List;

public interface ResultDashboardService {
    List<String> getTop3Riders(String raceId);

    List<String> getRidersWhoDidNotFinish(String raceId);

    List<String> getRidersWhoDidNotParticipate(String raceId);

    AppResponse<String> getRaceWeather(String raceId);

}
