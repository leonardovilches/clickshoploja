package com.clickshop.loja.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clickshop.loja.entities.Address;
import com.clickshop.loja.entities.Client;
import com.clickshop.loja.repositories.AddressRepository;
import com.clickshop.loja.repositories.ClientRepository;
import com.clickshop.loja.resources.AddressResource;
import com.clickshop.loja.services.exceptions.ObjectNotFoundException;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private ClientRepository clientRepository;
	
	@Transactional
	public Address create(Address addrObj) {
		addrObj.setId(null);
			
		 List<Address> addresses = findAllByClientId(addrObj.getClient().getId());
		 
		 if(addresses.size() < 4) {
			 return addressRepository.save(addrObj);
		 } 
		 else { 
			 throw new ObjectNotFoundException("Limite de 4 Itens Atingido"); 
		 }

	}

	public Address findById(Integer id) {

		Optional<Address> AddrEnt = addressRepository.findById(id);
			return AddrEnt.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto não encontrado! id: " + id + "; Tipo: " + Address.class.getName()));

	}
	
	public List<Address> findAllByClientId(Integer id) {
		return clientRepository.findAllByClientId(id);
	}
	

	public void delete(Integer id) {
		findById(id);
		addressRepository.deleteById(id);
	}
	
	public Address update(Integer id, Address addrEnt) {
		findById(id);
		
		addrEnt.setId(id);
			return addressRepository.save(addrEnt);
		
	}

	public Address fromResourceCreate(AddressResource addrResou, Client cli) {
		
		return new Address(addrResou.getId(), addrResou.getCity(), addrResou.getDistrict(),
				addrResou.getStreet(), addrResou.getComplement(), addrResou.getNumber(), cli);
	}
	
	public Address fromResourceUpdate(AddressResource addrResou, Integer id) {
		
		Address addr = findById(id);
		
		return new Address(addrResou.getId(), addrResou.getCity(), addrResou.getDistrict(),
				addrResou.getStreet(), addrResou.getComplement(), addrResou.getNumber(), addr.getClient());
	}

}
