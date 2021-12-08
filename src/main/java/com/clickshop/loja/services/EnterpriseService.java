package com.clickshop.loja.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clickshop.loja.entities.Address;
import com.clickshop.loja.entities.Client;
import com.clickshop.loja.entities.Enterprise;
import com.clickshop.loja.repositories.AddressRepository;
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
	private AddressRepository addressRepository;

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
			else if(priObj.getAddresses().size() > 5) {
				throw new ObjectAlreadyRegistered("Limite de Endereços Registrados; Tipo: " + Enterprise.class.getName());
			}
			
			Enterprise pri = enterpriseRepository.save(priObj);
			
			priObj.getAddresses().forEach((Address addr) -> {
				addr.setId(null);
				addr.setPerson(pri);
				addressRepository.save(addr);
			});
			
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
		else if(priEnt.getAddresses().size() > 5) {
			throw new ObjectAlreadyRegistered("Limite de Endereços Registrados; Tipo: " + Enterprise.class.getName());
		}
			
			updateAddress(priEnt);
		
			return enterpriseRepository.save(priEnt);
		
	}
	
	public void updateAddress(Enterprise priEnt) {
		
		List<Address> addresses = enterpriseRepository.findAddressByEnterpriseId(priEnt.getId());
		
		List<Address> newAddresses = priEnt.getAddresses();
		
		addresses.forEach((Address addr) -> {
			newAddresses.forEach((Address newAddr) -> {
				if(addr.getId() == newAddr.getId()) {
					newAddr.setPerson(priEnt);
					addressRepository.save(newAddr);
				}
				else {
					newAddr.setPerson(priEnt);
					addressRepository.save(newAddr);
				}
			});
		});
		
		addresses = enterpriseRepository.findAddressByEnterpriseId(priEnt.getId());
		
		addresses.forEach((Address addr) -> {
			if(!newAddresses.contains(addr)) {
				addressRepository.delete(addr);
			}
		});
		
	}

	public Enterprise fromResource(EnterpriseResource priResou) {
		
		return new Enterprise(priResou.getId(), priResou.getName(), priResou.getEnterpriseName(), priResou.getEmail(),
				priResou.getPhoneNumbers(), priResou.getAddresses());
	}
	
	
}
