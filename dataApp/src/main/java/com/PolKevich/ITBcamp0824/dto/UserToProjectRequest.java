package com.PolKevich.ITBcamp0824.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserToProjectRequest {

    private Long projectId;

    private Long userId;

}
