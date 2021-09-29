package com.clickshop.loja.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clickshop.loja.entities.Client;
import com.clickshop.loja.repositories.ClientRepository;
import com.clickshop.loja.resources.ClientResource;
import com.clickshop.loja.services.exceptions.ObjectAlreadyRegistered;
import com.clickshop.loja.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	ClientRepository clientRepository;

	@Transactional
	public Client create(Client cliObj) {
		cliObj.setId(null);
		Optional<Client> cliEntInstaUser = clientRepository.findByInstaUsername(cliObj.getInstaUsername());
		Optional<Client> cliEntEmail = clientRepository.findByEmail(cliObj.getEmail());
			if(cliEntInstaUser.isPresent()) {
				throw new ObjectAlreadyRegistered("Usuário do Instagram já Registrado; Tipo: " + Client.class.getName());
			}
			else if(cliEntEmail.isPresent()) {
				throw new ObjectAlreadyRegistered("Email já Registrado; Tipo: " + Client.class.getName());
			}
			
			return clientRepository.save(cliObj);

	}

	public Client findById(Integer id) {

		Optional<Client> cliEnt = clientRepository.findById(id);
			return cliEnt.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto não encontrado! id: " + id + "; Tipo: " + Client.class.getName()));

	}

	public boolean delete(Integer id) {
		findById(id);
		clientRepository.deleteById(id);
		return true;
	}

	public Client fromResource(ClientResource cliResou) {
		return new Client(cliResou.getId(), cliResou.getName(), cliResou.getInstaUsername(), cliResou.getEmail(),
				cliResou.getPhoneNumber());
	}

	public Client update(Client cliEnt) {
		findById(cliEnt.getId());
			return clientRepository.save(cliEnt);
		
	}

}
