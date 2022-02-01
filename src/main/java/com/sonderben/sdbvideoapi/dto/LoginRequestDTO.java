package com.sonderben.sdbvideoapi.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class LoginRequestDTO {
    @NotBlank(message = "email can not be empty")
    String email;
    @NotBlank(message = "email can not be empty")
    String password;
    @NotBlank(message = "email can not be empty")
    String device;
    String location;

    @Override
    public String toString() {
        return "LoginRequestDTO{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", IdDevice='" + device + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
