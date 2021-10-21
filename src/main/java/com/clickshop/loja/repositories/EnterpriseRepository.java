package com.clickshop.loja.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.clickshop.loja.entities.Address;
import com.clickshop.loja.entities.Enterprise;

@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, Integer> {

	
	Optional<Enterprise> findByEmail(String email);
	
	@Query("Select e.addresses from Enterprise e where e.id = :id")
	List<Address> findAddressByEnterpriseId(@Param("id") Integer id);
}
