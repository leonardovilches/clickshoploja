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
import com.clickshop.loja.resources.EnterpriseResource;
import com.clickshop.loja.services.exceptions.ObjectAlreadyRegistered;
import com.clickshop.loja.services.exceptions.ObjectNotFoundException;
import com.clickshop.loja.utils.Paginator;

@Service
public class EnterpriseService {
	
	@Autowired
	private EnterpriseRepository enterpriseRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private AddressService addressService;

	@Transactional
	public Enterprise create(Enterprise priObj) {
		priObj.setId(null);
		Optional<Enterprise> priEntEmail = enterpriseRepository.findByEmail(priObj.getEmail());
		Optional<Client> cliEntEmail = clientRepository.findByEmail(priObj.getEmail());
			if(priEntEmail.isPresent() || cliEntEmail.isPresent()) {
				throw new ObjectAlreadyRegistered("Email já Registrado; Tipo: " + Enterprise.class.getName());
			}
			else if(priObj.getPhoneNumbers().size() > 5) {
				throw new ObjectAlreadyRegistered("Limite de Numeros Registrados; Tipo: " + Enterprise.class.getName());
			}
			
			Enterprise pri = enterpriseRepository.save(priObj);
			
			if(!pri.getAddresses().isEmpty()) { 
				Set<Address> addresses = pri.getAddresses();
				
				for(Address i : addresses) { 
					i.setPerson(pri);
					addressService.create(i); 
			  } 
			}
			 
			
			return pri;

	}

	public Enterprise findById(Integer id) {

		Optional<Enterprise> priEnt = enterpriseRepository.findById(id);
			return priEnt.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto não encontrado! id: " + id + "; Tipo: " + Enterprise.class.getName()));

	}
	
	public List<Enterprise> findAll() {
		return enterpriseRepository.findAll();
	}
	
	public Page<Enterprise> findPage(Paginator paginator) {
		
		PageRequest pageRequest = PageRequest.of(
				paginator.getPageNumber(), paginator.getItemsPerPage(), Direction.valueOf(paginator.getDirection()), paginator.getOrderBy());
		
		return enterpriseRepository.findAll(pageRequest);
	}
	

	public void delete(Integer id) {
		findById(id);
		enterpriseRepository.deleteById(id);
	}
	
	public Enterprise update(Enterprise priEnt) {
		findById(priEnt.getId());
		if(priEnt.getPhoneNumbers().size() > 5) {
			throw new ObjectAlreadyRegistered("Limite de Numeros Registrados; Tipo: " + Enterprise.class.getName());
		}
			return enterpriseRepository.save(priEnt);
		
	}

	public Enterprise fromResource(EnterpriseResource priResou) {
		
		return new Enterprise(priResou.getId(), priResou.getName(), priResou.getEnterpriseName(), priResou.getEmail(),
				priResou.getPhoneNumbers(), priResou.getAddresses());
	}
	
	
}
