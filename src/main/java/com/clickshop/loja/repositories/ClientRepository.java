package com.clickshop.loja.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clickshop.loja.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
		
	Optional<Client> findByInstaUsername(String instaUsername);
	
	Optional<Client> findByEmail(String email);
}
