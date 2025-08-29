package com.RidgeRush.controller;

import com.RidgeRush.dto.request.BulkRaceResultCreationsDto;
import com.RidgeRush.dto.request.RaceResultCreationsDto;
import com.RidgeRush.dto.request.RaceResultUpdateDto;
import com.RidgeRush.dto.response.AppResponse;
import com.RidgeRush.dto.response.RaceResultResponse;
import com.RidgeRush.service.RaceResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/race-result")
public class RaceResultController {
    private final RaceResultService raceResultService;

    @PostMapping("/race-results")
    ResponseEntity<AppResponse<RaceResultResponse>> raceResult(@RequestBody @Valid RaceResultCreationsDto raceResult){
        return ResponseEntity.ok(raceResultService.raceResult(raceResult));
    }

    @PostMapping("/race-results/bulk")
    ResponseEntity<AppResponse<String>> raceResultsBulk(
            @RequestBody @Valid BulkRaceResultCreationsDto bulkRaceResult) {
        return ResponseEntity.ok(raceResultService.raceResultsBulk(bulkRaceResult));
    }

    @GetMapping("/{id}")
    ResponseEntity<AppResponse<RaceResultResponse>> getRaceResult(@PathVariable String id) {
        return ResponseEntity.ok(raceResultService.getRaceResultById(id));
    }

    @GetMapping
    ResponseEntity<AppResponse<List<RaceResultResponse>>> getAllRaceResults() {
        return ResponseEntity.ok(raceResultService.getAllRaceResults());
    }

    @PutMapping("/{id}")
    ResponseEntity<AppResponse<RaceResultResponse>> updateRaceResult(
            @PathVariable String id,
            @RequestBody RaceResultUpdateDto updateDto) {
        return ResponseEntity.ok(raceResultService.updateRaceResult(id, updateDto));
    }
}
