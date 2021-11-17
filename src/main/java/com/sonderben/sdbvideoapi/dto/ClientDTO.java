package com.sonderben.sdbvideoapi.dto;

import com.sonderben.sdbvideoapi.entity.Profile;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDTO {

    Long id;
    String email;
    List<ProfileDto> profileList;
}
