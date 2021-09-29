package com.clickshop.loja.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clickshop.loja.entities.Client;
import com.clickshop.loja.resources.ClientResource;
import com.clickshop.loja.services.ClientService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Api(value = "Client Controller")
@RestController
@RequestMapping(value = "/clientes")
public class ClientController {

	@Autowired
	private ClientService clientService;

	@ApiOperation(value = "Create a New Client")
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody ClientResource cliReq) {

		log.info("--@)> Starting / Create Client Controller");

		Client cliEnt = clientService.fromResource(cliReq);
		clientService.create(cliEnt);

		log.info("--@)> Returning / Create Client Controller");

		return ResponseEntity.ok().build();
	}

	@ApiOperation(value = "Find Client By ID")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Client> findById(@PathVariable Integer id) {

		log.info("--@)> Starting / FindById Client Controller");

		Client cliEnt = clientService.findById(id);

		log.info("--@)> Returning / FindById Client Controller");

		return ResponseEntity.ok().body(cliEnt);
	}

	@ApiOperation(value = "Delete Client By ID")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {

		log.info("--@)> Starting / Delete Client Controller");

		clientService.delete(id);

		log.info("--@)> Returning / Delete Client Controller");

		return ResponseEntity.ok().build();
	}

	@ApiOperation(value = "Update Client By ID")
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody ClientResource cliReq) {
		
		log.info("--@)> Starting / Update Client Controller");
		
		cliReq.setId(id);
		Client cliEnt = clientService.fromResource(cliReq);
		clientService.update(cliEnt);

		log.info("--@)> Returning / Update Client Controller");
		
		return ResponseEntity.ok().build();
	}
}
