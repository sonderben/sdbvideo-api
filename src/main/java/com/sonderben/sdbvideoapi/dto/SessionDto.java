package com.sonderben.sdbvideoapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class SessionDto {
    @NotBlank
    String email;
    @NotBlank
    String device;
}
