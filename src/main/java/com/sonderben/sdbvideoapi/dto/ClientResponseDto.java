package com.sonderben.sdbvideoapi.dto;

import com.sonderben.sdbvideoapi.entity.Profile;
import com.sonderben.sdbvideoapi.entity.Plan;
import lombok.*;

import java.util.Calendar;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientResponseDto {
    Long id;
    String email;
    String firstName;
    String LastName;
    Calendar birthday;
    String telephone;
    String country;
    String region;
    String city;
    String department;
    String postalCode;
    String password;
    Calendar dateClientCreate;
    Plan access;
    Boolean allProfilesCanCreateNewProfile;
    Profile mainProfile;
}
