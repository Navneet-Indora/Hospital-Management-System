package org.example.hospitalmanagement.security.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.hospitalmanagement.security.entity.Enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {
    //implemented UserDetails as it internally used by DaoAuthenticationProvider
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;  //ADMIN,DOCTOR,PATIENT


    //this tells Spring Security what roles this user has
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+ role.name()));
    }

    //what should be treated as username
    @Override
    public String getUsername() {
        return email;
    }

    //these 4 methods tell whether account is usable
    //true - account never expires
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //true-> account not locked
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //true means password valid forever
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //account active
    @Override
    public boolean isEnabled() {
        return true;
    }
}
