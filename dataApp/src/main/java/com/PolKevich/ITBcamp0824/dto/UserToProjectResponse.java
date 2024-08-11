package com.PolKevich.ITBcamp0824.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserToProjectResponse {

    private String projectName;
    private String projectDescription;
    private List<UserResponse> users;
}
