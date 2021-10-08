package com.clickshop.loja.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.clickshop.loja.entities.Address;
import com.clickshop.loja.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
		
	Optional<Client> findByInstaUsername(String instaUsername);
	
	Optional<Client> findByEmail(String email);
	
	@Query("Select c.addresses from Client c where c.id = :id")
	List<Address> findAllByClientId(@Param("id") Integer id);
}
