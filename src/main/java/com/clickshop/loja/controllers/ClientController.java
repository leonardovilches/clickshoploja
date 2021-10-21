package com.clickshop.loja.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clickshop.loja.entities.Client;
import com.clickshop.loja.resources.ClientResource;
import com.clickshop.loja.services.ClientService;
import com.clickshop.loja.utils.Paginator;
import com.google.gson.Gson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Api(value = "Client Controller")
@RestController
@RequestMapping(value = "/clientes")
public class ClientController {

	private static final Gson gson = new Gson();
	
	@Autowired
	private ClientService clientService;
	
	@ApiOperation(value = "Create a New Client")
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody ClientResource cliReq) {

		log.info("--> Starting / Create Client Controller");

		Client cliEnt = clientService.fromResource(cliReq);
		clientService.create(cliEnt);

		log.info("--> Returning / Create Client Controller");

		return ResponseEntity.ok().build();
	}

	@ApiOperation(value = "Find Client By ID")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Client> findById(@PathVariable Integer id) {

		log.info("--> Starting / FindById Client Controller");

		Client cliEnt = clientService.findById(id);

		log.info("--> Returning / FindById Client Controller");

		return ResponseEntity.ok().body(cliEnt);
	}
	
	@ApiOperation(value = "Find All Clients")
	@GetMapping
	public ResponseEntity<List<Client>> findAll() {
		
		log.info("--> Starting / FindAll Client Controller");

		List<Client> cliEnts = clientService.findAll();

		log.info("--> Returning / FindAll Client Controller");
		
		return ResponseEntity.ok().body(cliEnts);
	}
	
	@ApiOperation(value = "Find Clients Page")
	@GetMapping(value="/page")
	public ResponseEntity<Page<Client>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="itemsPerPage", defaultValue="24")Integer itemsPerPage,
			@RequestParam(value="orderBy", defaultValue="name")String orderBy,
			@RequestParam(value="direction", defaultValue="DESC")String direction) 
	{
		log.info("--> Starting / FindPage Client Controller");
		
		Page<Client> cliEnts = clientService.findPage(new Paginator(page, itemsPerPage, orderBy, direction));
		
		log.info("--> Returning / FindPage Client Controller");
		
		return ResponseEntity.ok().body(cliEnts);
		
		
	}

	@ApiOperation(value = "Delete Client By ID")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {

		log.info("--> Starting / Delete Client Controller");

		clientService.delete(id);

		log.info("--> Returning / Delete Client Controller");

		return ResponseEntity.ok(gson.toJson("Cliente Deletado"));
	}

	@ApiOperation(value = "Update Client By ID")
	@PutMapping(value = "/{id}")
	public ResponseEntity<Client> update(@PathVariable Integer id, @RequestBody ClientResource cliReq) {
		
		log.info("--> Starting / Update Client Controller");
		
		cliReq.setId(id);
		Client cliEnt = clientService.fromResource(cliReq);
		Client cliUpdated = clientService.update(cliEnt);

		log.info("--> Returning / Update Client Controller");
		
		return ResponseEntity.ok().body(cliUpdated);
	}
	
}
