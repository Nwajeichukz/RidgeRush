package com.RidgeRush.dto.response;

import com.RidgeRush.entity.RaceResult;
import com.RidgeRush.entity.Rider;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RiderDtoResponse {
    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private List<String> raceResultIds;

    public RiderDtoResponse(Rider rider) {
        this.id = rider.getId();
        this.firstName = rider.getFirstName();
        this.lastName = rider.getLastName();
        this.email = rider.getEmail();
        this.raceResultIds = rider.getRaceResults()
                .stream()
                .map(RaceResult::getId)
                .collect(Collectors.toList());
    }
}
