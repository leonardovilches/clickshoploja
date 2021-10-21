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

import com.clickshop.loja.entities.Enterprise;
import com.clickshop.loja.resources.EnterpriseResource;
import com.clickshop.loja.services.EnterpriseService;
import com.clickshop.loja.utils.Paginator;
import com.google.gson.Gson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Api(value = "Enterprise Controller")
@RestController
@RequestMapping(value = "/empresas")
public class EnterpriseController {

	private static final Gson gson = new Gson();
	
	@Autowired
	private EnterpriseService enterpriseService;
	
	@ApiOperation(value = "Create a New Enterprise")
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody EnterpriseResource priReq) {

		log.info("--> Starting / Create Enterprise Controller");

		Enterprise priEnt = enterpriseService.fromResource(priReq);
		enterpriseService.create(priEnt);

		log.info("--> Returning / Create Enterprise Controller");

		return ResponseEntity.ok().build();
	}

	@ApiOperation(value = "Find Enterprise By ID")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Enterprise> findById(@PathVariable Integer id) {

		log.info("--> Starting / FindById Enterprise Controller");

		Enterprise priEnt = enterpriseService.findById(id);

		log.info("--> Returning / FindById Enterprise Controller");

		return ResponseEntity.ok().body(priEnt);
	}
	
	@ApiOperation(value = "Find All Enterprises")
	@GetMapping
	public ResponseEntity<List<Enterprise>> findAll() {
		
		log.info("--> Starting / FindAll Enterprise Controller");

		List<Enterprise> priEnts = enterpriseService.findAll();

		log.info("--> Returning / FindAll Enterprise Controller");
		
		return ResponseEntity.ok().body(priEnts);
	}
	
	@ApiOperation(value = "Find Enterprises Page")
	@GetMapping(value="/page")
	public ResponseEntity<Page<Enterprise>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="itemsPerPage", defaultValue="24")Integer itemsPerPage,
			@RequestParam(value="orderBy", defaultValue="name")String orderBy,
			@RequestParam(value="direction", defaultValue="DESC")String direction) 
	{
		log.info("--> Starting / FindPage Enterprise Controller");
		
		Page<Enterprise> priEnts = enterpriseService.findPage(new Paginator(page, itemsPerPage, orderBy, direction));
		
		log.info("--> Returning / FindPage Enterprise Controller");
		
		return ResponseEntity.ok().body(priEnts);
		
		
	}

	@ApiOperation(value = "Delete Enterprise By ID")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {

		log.info("--> Starting / Delete Enterprise Controller");

		enterpriseService.delete(id);

		log.info("--> Returning / Delete Enterprise Controller");

		return ResponseEntity.ok(gson.toJson("Enterprisee Deletado"));
	}

	@ApiOperation(value = "Update Enterprise By ID")
	@PutMapping(value = "/{id}")
	public ResponseEntity<Enterprise> update(@PathVariable Integer id, @RequestBody EnterpriseResource priReq) {
		
		log.info("--> Starting / Update Enterprise Controller");
		
		priReq.setId(id);
		Enterprise priEnt = enterpriseService.fromResource(priReq);
		Enterprise priUpdated = enterpriseService.update(priEnt);

		log.info("--> Returning / Update Enterprise Controller");
		
		return ResponseEntity.ok().body(priUpdated);
	}
	
}
