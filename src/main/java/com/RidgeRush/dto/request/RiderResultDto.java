package com.RidgeRush.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RiderResultDto {
    @NotEmpty(message = "riderId most not be empty")
    private String riderId;

    private String finishTime;
    private Integer totalTimeSeconds;
    private Integer position;
}
