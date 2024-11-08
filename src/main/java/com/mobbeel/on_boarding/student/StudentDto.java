package com.mobbeel.on_boarding.student;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record StudentDto(
        @NotEmpty(message = "Students must have a firstname!")
        @NotBlank
        String firstname,
        @NotEmpty(message = "Students must have a lastname!")
        @NotBlank
        String lastname,
        @Email
        String email,
        Integer schoolId
) {
}
