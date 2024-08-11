package com.PolKevich.ITBcamp0824.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProjectRequest {

    @Size(max = 60,min = 2)
    @Pattern(regexp = "^[a-zA-Z .-]*$")
    String projectName;

    @Size(max = 150,min = 2)
    @Pattern(regexp = "^[a-zA-Z .-]*$")
    String projectDescription;
}
