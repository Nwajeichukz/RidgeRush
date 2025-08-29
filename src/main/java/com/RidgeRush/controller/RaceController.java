package com.RidgeRush.controller;

import com.RidgeRush.dto.request.BulkRaceResultCreationsDto;
import com.RidgeRush.dto.request.RaceUpdateDto;
import com.RidgeRush.dto.response.AppResponse;
import com.RidgeRush.dto.request.RaceCreationDto;
import com.RidgeRush.dto.request.RaceResultCreationsDto;
import com.RidgeRush.dto.response.RaceResponse;
import com.RidgeRush.dto.response.RaceResultResponse;
import com.RidgeRush.entity.Race;
import com.RidgeRush.service.RaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/race")
public class RaceController {
    private final RaceService raceService;

    @PostMapping("/create-race")
    ResponseEntity<AppResponse<String>> createRace(@RequestBody @Valid RaceCreationDto creationDto){
        return ResponseEntity.ok(raceService.createRace(creationDto));
    }

    @GetMapping("/{raceId}")
    ResponseEntity<AppResponse<RaceResponse>> getRace(@PathVariable String raceId){
        return ResponseEntity.ok(raceService.getRaceById(raceId));
    }

    @PutMapping("/update/{raceId}")
    ResponseEntity<AppResponse<String>> updateRace(@PathVariable String raceId,
                                                   @RequestBody RaceUpdateDto updateDto){
        return ResponseEntity.ok(raceService.updateRace(raceId, updateDto));
    }


}
