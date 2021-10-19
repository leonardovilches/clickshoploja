package com.clickshop.loja.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clickshop.loja.entities.Address;
import com.clickshop.loja.entities.Client;
import com.clickshop.loja.entities.Enterprise;
import com.clickshop.loja.repositories.ClientRepository;
import com.clickshop.loja.repositories.EnterpriseRepository;
import com.clickshop.loja.resources.ClientResource;
import com.clickshop.loja.services.exceptions.ObjectAlreadyRegistered;
import com.clickshop.loja.services.exceptions.ObjectNotFoundException;
import com.clickshop.loja.utils.Paginator;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private EnterpriseRepository enterpriseRepository;
	
	@Autowired
	private AddressService addressService;

	@Transactional
	public Client create(Client cliObj) {
		cliObj.setId(null);
		Optional<Client> cliEntInstaUser = clientRepository.findByInstaUsername(cliObj.getInstaUsername());
		Optional<Client> cliEntEmail = clientRepository.findByEmail(cliObj.getEmail());
		Optional<Enterprise> priEntEmail = enterpriseRepository.findByEmail(cliObj.getEmail());
			if(cliEntInstaUser.isPresent()) {
				throw new ObjectAlreadyRegistered("Usuário do Instagram já Registrado; Tipo: " + Client.class.getName());
			}
			else if(cliEntEmail.isPresent() || priEntEmail.isPresent()) {
				throw new ObjectAlreadyRegistered("Email já Registrado; Tipo: " + Client.class.getName());
			}
			else if(cliObj.getPhoneNumbers().size() > 5) {
				throw new ObjectAlreadyRegistered("Limite de Numeros Registrados; Tipo: " + Client.class.getName());
			}
			
			Client cli = clientRepository.save(cliObj);
			
			if(!cli.getAddresses().isEmpty()) { 
				Set<Address> addresses = cli.getAddresses();
				
				for(Address i : addresses) { 
					i.setPerson(cli);
					addressService.create(i); 
			  } 
			}
			 
			
			return cli;

	}

	public Client findById(Integer id) {

		Optional<Client> cliEnt = clientRepository.findById(id);
			return cliEnt.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto não encontrado! id: " + id + "; Tipo: " + Client.class.getName()));

	}
	
	public List<Client> findAll() {
		return clientRepository.findAll();
	}
	
	public Page<Client> findPage(Paginator paginator) {
		
		PageRequest pageRequest = PageRequest.of(
				paginator.getPageNumber(), paginator.getItemsPerPage(), Direction.valueOf(paginator.getDirection()), paginator.getOrderBy());
		
		return clientRepository.findAll(pageRequest);
	}
	
	@Transactional
	public void delete(Integer id) {
		findById(id);
		clientRepository.deleteById(id);
	}
	
	@Transactional
	public Client update(Client cliEnt) {
		findById(cliEnt.getId());
		if(cliEnt.getPhoneNumbers().size() > 5) {
			throw new ObjectAlreadyRegistered("Limite de Numeros Registrados; Tipo: " + Client.class.getName());
		}
			return clientRepository.save(cliEnt);
		
	}

	public Client fromResource(ClientResource cliResou) {
		
		return new Client(cliResou.getId(), cliResou.getName(), cliResou.getInstaUsername(), cliResou.getEmail(),
				cliResou.getPhoneNumbers(), cliResou.getAddresses());
	}
	
	
}
