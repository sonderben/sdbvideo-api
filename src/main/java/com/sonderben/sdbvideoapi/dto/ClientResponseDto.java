package com.sonderben.sdbvideoapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sonderben.sdbvideoapi.entity.Profile;
import com.sonderben.sdbvideoapi.entity.TypeAccess;
import lombok.*;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

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
    TypeAccess access;
    Boolean allProfilesCanCreateNewProfile;
    Profile mainProfile;
}
