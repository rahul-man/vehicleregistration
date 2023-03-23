package com.wipro.asg.vehicleregistration.model;

import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private boolean active;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private ApplicationUserRole role;

    @Column(name = "authorities")
    private String authorities;

    public User(String username, String password, ApplicationUserRole role, String authorities) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.authorities = authorities;
        this.active = true;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean getActive() {
        return active;
    }

    public Set<SimpleGrantedAuthority> getRole() {
        return role.grantedAuthorities();
    }
}
