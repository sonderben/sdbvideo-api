package com.sonderben.sdbvideoapi.entity;

import com.sonderben.sdbvideoapi.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/*import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;*/

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "ADMINISTRATOR")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Administrator extends BaseEntity /*implements UserDetails,*/implements Serializable {
    @Column(nullable = false)
    @Size(min = 3,message = "Bad full name, size must be greater than 3")
    String fullName;
    @Email(message = "Bad email, does not correspond to the normal form of an email")
    @NotBlank(message = "Bad email, can not be blank")
    @Column(nullable = false,unique = true)
    String email;
    @Column(nullable = false)
    @NotBlank(message = "Bad password, can not be blank")
    @NotNull(message = "Bad password, can not be null")
    @Size(min = 3,message = "Bad password, size must be greater than 3")
    String password;

    //@Override
   /* public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @Override
    public String getPassword() {
        return password;
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
    }*/
}
