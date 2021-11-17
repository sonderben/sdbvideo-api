package com.sonderben.sdbvideoapi.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponseDTO {
    private ClientDTO user;
    private String token;
}
