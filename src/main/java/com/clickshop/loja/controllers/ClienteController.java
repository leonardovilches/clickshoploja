package com.clickshop.loja.controllers;

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

import com.clickshop.loja.entities.ClienteEntity;
import com.clickshop.loja.resources.ClienteResource;
import com.clickshop.loja.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody ClienteResource cliReq) {
		
		ClienteEntity cliEnt = clienteService.fromResource(cliReq);
		clienteService.create(cliEnt);
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<ClienteEntity> findById(@PathVariable Integer id) {
		ClienteEntity cliEnt = clienteService.findById(id);
		return ResponseEntity.ok().body(cliEnt);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
		clienteService.delete(id);
		
		return ResponseEntity.ok().build();
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Void> updateById(@PathVariable Integer id, @RequestBody ClienteResource cliReq) {
		cliReq.setId(id);
		ClienteEntity cliEnt = clienteService.fromResource(cliReq);
		clienteService.update(cliEnt);
		
		return ResponseEntity.ok().build();
	}
}
