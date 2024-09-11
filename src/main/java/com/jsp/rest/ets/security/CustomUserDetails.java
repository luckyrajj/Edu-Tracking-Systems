package com.jsp.rest.ets.security;

import com.jsp.rest.ets.user.Privilege;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import com.jsp.rest.ets.user.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> grantedAuthorities=new ArrayList<>();
//        List<Privilege> privileges=user.getRole().getPrivileges();
//        for(Privilege privilege:privileges)
//            grantedAuthorities.add(new SimpleGrantedAuthority("privilege"));
//        return grantedAuthorities;
        return user.getRole().getPrivileges()
                .stream().map(privilege ->new SimpleGrantedAuthority(privilege.name())).toList();

    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }
}
