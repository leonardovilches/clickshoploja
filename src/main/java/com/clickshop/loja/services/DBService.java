package com.clickshop.loja.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		
		Set<String> tel1 = new HashSet<String>();
		tel1.add("+5517991151413");
		
		Set<String> tel2 = new HashSet<String>();
		tel2.add("+5517991531234");
		
		Set<String> tel3 = new HashSet<String>();
		tel3.add("+5517991538899");		
		
		Client cli1 = new Client(null, "Leonardo Vilches","leovilches08", "leovilches08@gmail.com", tel1, null);
		Client cli2 = new Client(null, "Denilson Fontes","denilsonFontes", "denilsinho@gmail.com", tel2, null);
		Client cli3 = new Client(null, "Raquel Fontes","raquelFontes", "raquelfontes@gmail.com", tel3, null);
		
		clientRepository.saveAll(Arrays.asList(cli1, cli2, cli3));
		
		Set<String> telefone1 = new HashSet<String>();
		telefone1.add("+5517991531742");
		
		Set<String> telefone2 = new HashSet<String>();
		telefone2.add("+5517991531742");
		
		Set<String> telefone3 = new HashSet<String>();
		telefone3.add("+5517991531662");
		telefone3.add("+5517991531468");
		Enterprise ent1 = new Enterprise(null, "Loja Valeria Tamarindo","Espaço Valeria", "valeriaTamarindo@gmail.com", telefone3, null);
		Enterprise ent2 = new Enterprise(null, "Duloli","Duloli Fitness", "duloli@gmail.com", telefone1, null);
		Enterprise ent3 = new Enterprise(null, "Koceira Store","Koceira Store", "koceira@gmail.com", telefone2, null);
		
		enterpriseRepository.saveAll(Arrays.asList(ent1, ent2, ent3));
		
		Live live1 = new Live(null, new Date(), 10L, 5L, 2L, ent1);
		Live live2 = new Live(null, new Date(), 10L, 5L, 2L, ent2);
		Live live3 = new Live(null, new Date(), 10L, 5L, 2L, ent3);
		
		liveRepository.saveAll(Arrays.asList(live1, live2, live3));
		
		User user1 = new User(null, "Leonardo Vilches", "36367733809", "leovilches08@gmail.com", "17991531742", "12345");
		User user2 = new User(null, "Leonardo Vilches", "36367733808", "denilsinho@gmail.com", "17992554111", "12345");
		
		userRepository.saveAll(Arrays.asList(user1, user2));
		

		Address address1 = new Address(null, "Mirassolândia", "Centro", "Francisco Broisler", "Casa", 368, cli1);
		Address address2 = new Address(null, "Tanabi", "Jd. Sônia", "Alberto Andaló", "Casa", 514, cli2);
		Address address3 = new Address(null, "Mirassolândia", "Centro", "Francisco Broisler", "Casa", 26, cli3);
		List<Address> listAdd1 = new ArrayList<Address>();
		listAdd1.add(address1);
		
		List<Address> listAdd2 = new ArrayList<Address>();
		listAdd1.add(address2);
		
		List<Address> listAdd3 = new ArrayList<Address>();
		listAdd1.add(address3);
		
		Address addressEnt1 = new Address(null, "Rio Preto", "Centro", "Benjamin Constant", "AP", 74, ent1);
		Address addressEnt2 = new Address(null, "Mirassol", "Jd. Rosangela", "Manoel Batista", "Casa", 54, ent2);
		Address addressEnt3 = new Address(null, "Mirassolândia", "Centro", "Joao Batista", "Casa", 444, ent3);
		
		addressRepository.saveAll(Arrays.asList(address1, address2, address3, addressEnt1, addressEnt2, addressEnt3));
		
		
		
		
				
	}
}
