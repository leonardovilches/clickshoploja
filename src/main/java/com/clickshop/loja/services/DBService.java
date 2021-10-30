package com.clickshop.loja.services;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clickshop.loja.entities.Address;
import com.clickshop.loja.entities.Client;
import com.clickshop.loja.entities.Enterprise;
import com.clickshop.loja.entities.Live;
import com.clickshop.loja.entities.User;
import com.clickshop.loja.repositories.AddressRepository;
import com.clickshop.loja.repositories.ClientRepository;
import com.clickshop.loja.repositories.EnterpriseRepository;
import com.clickshop.loja.repositories.LiveRepository;
import com.clickshop.loja.repositories.UserRepository;

@Service
public class DBService {

	@Autowired
	private LiveRepository liveRepository;
	
	@Autowired
	private EnterpriseRepository enterpriseRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public void instatiateTestDatabase() {
		
		Client cli1 = new Client(null, "Leonardo Vilches","leovilches08", "leovilches08@gmail.com", null, null);
		Client cli2 = new Client(null, "Denilson Fontes","denilsonFontes", "denilsinho@gmail.com", null, null);
		Client cli3 = new Client(null, "Raquel Fontes","raquelFontes", "rquelfontes@gmail.com", null, null);
		
		clientRepository.saveAll(Arrays.asList(cli1, cli2, cli3));
		
		Enterprise ent1 = new Enterprise(null, "Loja Valeria Tamarindo","Espaço Valeria", "valeriaTamarindo@gmail.com", null, null);
		Enterprise ent2 = new Enterprise(null, "Duloli","Duloli Fitness", "duloli@gmail.com", null, null);
		Enterprise ent3 = new Enterprise(null, "Koceira Store","Koceira Store", "koceira@gmail.com", null, null);
		
		enterpriseRepository.saveAll(Arrays.asList(ent1, ent2, ent3));
		
		Live live1 = new Live(null, new Date(), 10L, 5L, 2L, ent1);
		Live live2 = new Live(null, new Date(), 10L, 5L, 2L, ent2);
		Live live3 = new Live(null, new Date(), 10L, 5L, 2L, ent3);
		
		liveRepository.saveAll(Arrays.asList(live1, live2, live3));
		
		User user1 = new User();
		User user2 = new User();
		User user3 = new User();
		
		Address address1 = new Address();
		Address address2 = new Address();
		Address address3 = new Address();
		
		
		
				
	}
}
