package com.clickshop.loja.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clickshop.loja.entities.ClienteEntity;
import com.clickshop.loja.repositories.ClienteRepository;
import com.clickshop.loja.resources.ClienteResource;

import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	
	public ClienteEntity create(ClienteEntity cliObj) {
		cliObj.setId(null);
		
		try {
			return clienteRepository.save(cliObj);
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public ClienteEntity findById(Integer id) {

		Optional<ClienteEntity> cliEnt = clienteRepository.findById(id);
		
		try {
			return cliEnt.orElseThrow(() -> new ObjectNotFoundException(
					"Object not found! by id: " + id + "\nType: " + ClienteEntity.class.getName()));
		}
		catch(ObjectNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
		
	}
	
	public boolean delete(Integer id) {

		try {
			clienteRepository.deleteById(id);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public ClienteEntity fromResource(ClienteResource cliResou) {
		return new ClienteEntity(
				cliResou.getId(), cliResou.getName(), cliResou.getInstaUsername(),
				cliResou.getEmail(), cliResou.getPhoneNumber());
	}

	public ClienteEntity update(ClienteEntity cliEnt) {
		try {
			return clienteRepository.save(cliEnt);
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
