package com.RidgeRush.dto.request;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RaceResultCreationsDto {
    @NotEmpty(message = "raceId most not be empty")
    private String raceId;

    @NotEmpty(message = "riderId most not be empty")
    private String riderId;

    @NotEmpty(message = "finishTime most not be empty")
    private String finishTime;

    @NotEmpty(message = "totalTimeSeconds most not be empty")
    private Integer totalTimeSeconds;

    @NotEmpty(message = "position most not be empty")
    private Integer position;

}
