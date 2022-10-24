package com.tabdaul.tabanewsapp.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name = "USERS")
public class User implements UserDetails {

    @Id
    @SequenceGenerator(
            name = "user_seq",
            sequenceName = "user_seq"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_seq"
    )
    @Column(name = "id", nullable = false)
    private Long id;

    @NotEmpty
    private String username;

    private String mobileNo;

    @NotEmpty
    private String email;

    @NotEmpty
    @JsonIgnore // FOR FUTURE YOU: this is used to remark that this field won't be constructed in JSON response
    private String password;

    private Boolean locked = false;
    private Boolean enabled = true;

    public User() {}

    public User(@NotEmpty String username, @NotEmpty String email, @NotEmpty String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
