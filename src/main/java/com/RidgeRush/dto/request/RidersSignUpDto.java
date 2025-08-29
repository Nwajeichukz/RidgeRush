package com.RidgeRush.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class RidersSignUpDto {
    @NotEmpty(message = "firstname most not be empty")
    private String firstName;

    @NotEmpty(message = "lastName most not be empty")
    private String lastName;

    @NotEmpty(message = "email most not be empty")
    @Email(message = "use email format")
    private String email;
}
