package com.PolKevich.ITBcamp0824.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserRequest {

    @Size(max = 40,min = 2)
    @Pattern(regexp = "^[a-zA-Z]*$")
    private String lastname;

    @Size(max = 20,min = 2)
    @Pattern(regexp = "^[a-zA-Z]*$")
    private String firstname;

    @Size(max = 40)
    @Pattern(regexp = "^[a-zA-Z]*$")
    private String patronymic;

    @Size(max = 50)
    @Email
    private String email;

    @Size(max = 40)
    @Pattern(regexp = "^[a-zA-Z]*$")
    private String post;
}
