package com.resortbooking.application.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.resortbooking.application.dao.UserRepository;
import com.resortbooking.application.models.User;

public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String value) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByEmail(value);
		
		if(user.isEmpty()) {
			throw new UsernameNotFoundException("Could not find user");
		}
		return new ResortUserDetails(user.get());
	}
}
