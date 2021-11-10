package com.clickshop.loja.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.clickshop.loja.entities.User;
import com.clickshop.loja.repositories.UserRepository;
import com.clickshop.loja.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByEmail(email);
		if(user.isEmpty()) {
			throw new UsernameNotFoundException(email);
		}
		
		User userData = user.get();
		
		return new UserSS(userData.getId(), userData.getEmail(), userData.getPassword(), userData.getpermissionLevels());
	}

}
