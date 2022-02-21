package com.sonderben.sdbvideoapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sonderben.sdbvideoapi.entity.base.BaseEntity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "CLIENTS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Client extends BaseEntity implements UserDetails,Serializable {
    @Column(unique = true,nullable = false)
    String email;
    @Column(length = 50)
    String firstName;
    @Column(length = 50)
    String LastName;
    @Temporal(TemporalType.DATE)
    Calendar birthday;
    @Column(length = 11)
    /*@Pattern(regexp = "^(\\d{10})|(([\\(]?([0-9]{3})[\\)]?)?[ \\.\\-]?([0-9]{3})[ \\.\\-]([0-9]{4}))$"
            ,message = "Bad cel number")*/
    String telephone;
    @Column(length = 50)
    String country;
    @Column(length = 50)
    String region;
    @Column(length = 50)
    String city;
    @Column(length = 50)
    String department;
    @Column(length = 16)
    String postalCode;
    String password;
    String sex;
    @Temporal(TemporalType.TIMESTAMP)
   //@Column(insertable = false,updatable = false)
    Calendar dateClientCreate;
    @OneToOne
    @NotNull
    Plan plan;
    @Column(columnDefinition = "boolean default false",insertable = false)
    Boolean allProfilesCanCreateNewProfile;
    @JsonIgnore
    @OneToMany(mappedBy = "client",cascade = {CascadeType.REMOVE})
    List<Profile> profileList;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections
                .singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    @Override
    public String getPassword() {
        return password;
    }
}
