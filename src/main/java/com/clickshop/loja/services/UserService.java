package com.clickshop.loja.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clickshop.loja.entities.User;
import com.clickshop.loja.repositories.UserRepository;
import com.clickshop.loja.resources.UserResource;
import com.clickshop.loja.services.exceptions.ObjectAlreadyRegistered;
import com.clickshop.loja.services.exceptions.ObjectNotFoundException;
import com.clickshop.loja.utils.Paginator;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Transactional
	public User create(User userObj) {
		userObj.setId(null);
		Optional<User> userEmail = userRepository.findByEmail(userObj.getEmail());
			if(userEmail.isPresent()) {
				throw new ObjectAlreadyRegistered("Email já Registrado; Tipo: " + User.class.getName());
			}
			
			return userRepository.save(userObj);

	}

	public User findById(Integer id) {

		Optional<User> user = userRepository.findById(id);
			return user.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto não encontrado! id: " + id + "; Tipo: " + User.class.getName()));

	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public Page<User> findPage(Paginator paginator) {
		
		PageRequest pageRequest = PageRequest.of(
				paginator.getPageNumber(), paginator.getItemsPerPage(), Direction.valueOf(paginator.getDirection()), paginator.getOrderBy());
		
		return userRepository.findAll(pageRequest);
	}
	

	public void delete(Integer id) {
		findById(id);
		userRepository.deleteById(id);
	}
	
	public User update(User user) {
		findById(user.getId());
			return userRepository.save(user);
		
	}

	public User fromResource(UserResource userResou) {
		
		return new User(userResou.getId(), userResou.getName(), userResou.getCpf(), 
				userResou.getEmail(), userResou.getPhoneNumber(), pe.encode(userResou.getPassword()));
	}
}
