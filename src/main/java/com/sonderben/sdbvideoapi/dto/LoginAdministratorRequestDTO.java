package com.sonderben.sdbvideoapi.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginAdministratorRequestDTO {
    String email;
    String password;
}
