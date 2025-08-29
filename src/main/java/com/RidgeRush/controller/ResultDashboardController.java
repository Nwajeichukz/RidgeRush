package com.RidgeRush.controller;

import com.RidgeRush.dto.response.AppResponse;
import com.RidgeRush.dto.response.RaceResultResponse;
import com.RidgeRush.entity.Rider;
import com.RidgeRush.service.ResultDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("dashboard")
public class ResultDashboardController {
    private final ResultDashboardService resultDashboardService;

    @GetMapping("/top-riders/{raceId}")
    public ResponseEntity<List<String>> getTop3FastestRiders(@PathVariable String raceId) {
        return ResponseEntity.ok(resultDashboardService.getTop3Riders(raceId));
    }

    @GetMapping("/dnf/{raceId}")
    public ResponseEntity<List<String>> getRidersWhoDidNotFinish(@PathVariable String raceId) {
        return ResponseEntity.ok(resultDashboardService.getRidersWhoDidNotFinish(raceId));
    }

    @GetMapping("/not-participated/{raceId}")
    public ResponseEntity<List<String>> getRidersWhoDidNotParticipate(@PathVariable String raceId) {
        return ResponseEntity.ok(resultDashboardService.getRidersWhoDidNotParticipate(raceId));
    }
    @GetMapping("/{raceId}/weather")
    public AppResponse<String> getRaceWeather(@PathVariable String raceId) {
        return resultDashboardService.getRaceWeather(raceId);
    }

}
