package com.RidgeRush.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RaceCreationDto {
    @NotEmpty(message = "name most not be empty")
    private String name;

    @NotEmpty(message = "location most not be empty")
    private String location;

    @NotEmpty(message = "startTime most not be empty")
    private String startTime;
}
