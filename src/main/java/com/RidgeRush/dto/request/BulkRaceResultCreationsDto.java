package com.RidgeRush.dto.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class BulkRaceResultCreationsDto {
    @NotEmpty(message = "raceId most not be empty")
    private String raceId;

    @NotEmpty(message = "riders most not be empty")
    private List<RiderResultDto> riders;

}
