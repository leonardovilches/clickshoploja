package com.clickshop.loja.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clickshop.loja.entities.Client;
import com.clickshop.loja.resources.PhoneNumberResource;
import com.clickshop.loja.services.exceptions.ObjectNotFoundException;

@Service
public class PhoneNumberService {

	@Autowired
	private ClientService clientService;
	
	public Client create(Integer id, PhoneNumberResource phoneNumber) {
		
		Client cliEnt = clientService.findById(id);
		Set<String> numbers = cliEnt.getPhoneNumbers();
		
		if(!numbers.contains(phoneNumber.getNewPhoneNumber())) {
			if(numbers.size() < 4) {
				numbers.add(phoneNumber.getNewPhoneNumber());
				cliEnt.setPhoneNumbers(numbers);
				clientService.update(cliEnt);
			}
			else {
				throw new ObjectNotFoundException("Limite de 4 Itens Atingido");
			}
		}
		else {
			throw new ObjectNotFoundException(
					"Número já registrado! numero: " + phoneNumber.getNewPhoneNumber() + "; Tipo: " + Client.class.getName());
		}	
		return cliEnt;	
	}
	
	public Client update(Integer id, PhoneNumberResource phoneNumber, String currentNumber) {
		
		Client cliEnt = clientService.findById(id);
		Set<String> numbers = cliEnt.getPhoneNumbers();
		
		if(numbers.contains(currentNumber)) {
			numbers.remove(currentNumber);
			numbers.add(phoneNumber.getNewPhoneNumber());
	
			cliEnt.setPhoneNumbers(numbers);
			clientService.update(cliEnt);
		}
		else {
			throw new ObjectNotFoundException(
					"Número não encontrado! numero: " + currentNumber + "; Tipo: " + Client.class.getName());
		}	
		return cliEnt;	
	}
	
	public void delete(Integer id, String currentNumber) {
		
		Client cliEnt = clientService.findById(id);
		Set<String> numbers = cliEnt.getPhoneNumbers();
		
		if(numbers.contains(currentNumber)) {
			numbers.remove(currentNumber);
	
			cliEnt.setPhoneNumbers(numbers);
			clientService.update(cliEnt);
		}
		else {
			throw new ObjectNotFoundException(
					"Número não encontrado! numero: " + currentNumber + "; Tipo: " + Client.class.getName());
		}	
	}

	
}
