package com.sonderben.sdbvideoapi.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class administratorDTO {

    Long id;
    String fullName;
    String email;

}
