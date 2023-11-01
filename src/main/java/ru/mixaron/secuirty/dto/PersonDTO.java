package ru.mixaron.secuirty.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PersonDTO {

    @Email
    @NotEmpty
    private String email;

    @Size(min=3, max = 16, message = "Size: 3 between 16")
    @NotEmpty
    private String password;

    private String username;
}
