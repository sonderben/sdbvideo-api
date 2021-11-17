package com.sonderben.sdbvideoapi.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponseAdministratorDTO {
    private administratorDTO user;
    private String token;
}
