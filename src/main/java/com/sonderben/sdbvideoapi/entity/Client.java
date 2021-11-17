package com.sonderben.sdbvideoapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sonderben.sdbvideoapi.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "CLIENTS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Client extends BaseEntity implements UserDetails,Serializable {
    @Column(unique = true)
    String email;
    @Column(length = 50)
    String firstName;
    @Column(length = 50)
    String LastName;
    @Temporal(TemporalType.DATE)
    Calendar birthday;
    @Column(length = 11)
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
    @Temporal(TemporalType.TIMESTAMP)
    Calendar dateClientCreate;
    @OneToOne
    TypeAccess access;
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
