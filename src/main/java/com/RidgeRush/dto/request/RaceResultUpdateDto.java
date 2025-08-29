package com.RidgeRush.dto.request;

import lombok.*;

@Data
public class RaceResultUpdateDto {
    private String finishTime;
    private Integer totalTimeSeconds;
    private Integer position;
    private Boolean didNotFinish;
}
