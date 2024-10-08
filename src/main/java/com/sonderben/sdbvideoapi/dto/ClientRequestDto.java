package com.sonderben.sdbvideoapi.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.sonderben.sdbvideoapi.entity.Plan;
import lombok.*;


import javax.validation.constraints.*;
import java.util.Calendar;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientRequestDto {
    @Email(message = "Bad email, does not correspond to the normal form of an email")
    String email;
    @NotBlank(message = "Bad name, can not be blank and can not be null")
    @Size(min = 3,message = "Bad firstName, size must be greater than 3")
    String firstName;
    @NotBlank(message = "Bad LastName, can not be blank and can not be null")
    @Size(min = 3,message = "Bad LastName, size must be greater than 3")
    String LastName;
    //String birthday; ///1921 10 28
    Calendar birthday;
   /* @Pattern(regexp = "^(\\d{10})|(([\\(]?([0-9]{3})[\\)]?)?[ \\.\\-]?([0-9]{3})[ \\.\\-]([0-9]{4}))$"
            ,message = "Bad cel number")*/
    String telephone;
    String country;
    String region;
    String city;
    @NotBlank(message = "Bad sex, can not be blank and can not be null")
    String sex;
    String department;
    String postalCode;
    @NotBlank(message = "Bad password, can not be blank and can not be null")
    @Size(min = 3,message = "Bad password, size must be greater than 3")
    String password;
    //@NotNull(message = "access can not be null")
    Plan access;
    @NotNull(message = "allProfilesCanCreateNewProfile can not be blank and can not be null")
    Boolean allProfilesCanCreateNewProfile;
    @JsonProperty("access_id")
    private void unpackNested(Long access_id){
        if(access_id!=null) {
            this.access = new Plan();
            access.setId(access_id);
        }
    }

}
