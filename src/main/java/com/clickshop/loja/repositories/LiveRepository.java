package com.clickshop.loja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clickshop.loja.entities.Live;

@Repository
public interface LiveRepository extends JpaRepository<Live, Integer>{

}
