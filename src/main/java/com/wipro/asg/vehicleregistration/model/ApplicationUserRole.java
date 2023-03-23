package com.wipro.asg.vehicleregistration.model;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.wipro.asg.vehicleregistration.model.ApplicationUserAuthority.*;

@Getter
@AllArgsConstructor
public enum ApplicationUserRole {

    USER(Sets.newHashSet(CREATE_REQUEST, VIEW_REQUEST)),
    ADMIN(Sets.newHashSet(APPROVE_REQUEST, DECLINE_REQUEST, SEARCH_REQUEST));

    private final Set<ApplicationUserAuthority> permissions;

    public Set<SimpleGrantedAuthority> grantedAuthorities() {
        Set<SimpleGrantedAuthority> authorities = permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermissions()))
                .collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }

    public String getAuthorities() {
        return permissions.stream().map(ApplicationUserAuthority::getPermissions).collect(Collectors.joining(", "));
    }
}
