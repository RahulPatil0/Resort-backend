package com.resortbooking.application.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.resortbooking.application.models.Roles;
import com.resortbooking.application.models.User;

public class ResortUserDetails implements UserDetails{

	public ResortUserDetails(User user) {
		super();
		this.user = user;
	}

	private static final long serialVersionUID = 1L;
	
	private User user;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Roles> roles = user.getRoles();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();

		for (Roles role : roles) {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole()));
		}

		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getFirstName() + user.getLastName();
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
}
