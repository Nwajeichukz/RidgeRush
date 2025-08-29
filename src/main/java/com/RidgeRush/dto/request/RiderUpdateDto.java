package com.RidgeRush.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class RiderUpdateDto {

    private String firstName;
    private String lastName;

    @Email
    private String email;
}
