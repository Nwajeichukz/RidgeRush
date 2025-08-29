package com.RidgeRush.dto.response;

import com.RidgeRush.entity.RaceResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RaceResultResponse {
    private String id;

    private String raceId;

    private String riderId;

    private String finishTime;

    private Integer totalTimeSeconds;

    private Integer position;

    private Boolean didNotFinish = false;

    public RaceResultResponse(RaceResult raceResult) {
        this.id = raceResult.getId();
        this.raceId = raceResult.getRace().getId();
        this.riderId = raceResult.getRider().getId();
        this.finishTime = raceResult.getFinishTime();
        this.totalTimeSeconds = raceResult.getTotalTimeSeconds();
        this.position = raceResult.getPosition();
        this.didNotFinish = raceResult.getDidNotFinish();
    }
}
