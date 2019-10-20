package com.example.lgcns.service;

import java.util.stream.Collectors;

import com.example.lgcns.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * UserDetailsServiceImpl
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsernameIgnoreCase(username).map(user -> {
			return new User(user.getUsername(), user.getPassword(), user.getRoles().stream()
					.map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList()));
		}).orElseThrow(() -> new UsernameNotFoundException(username));
	}

}