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

import com.clickshop.loja.entities.User;
import com.clickshop.loja.resources.UserResource;
import com.clickshop.loja.services.UserService;
import com.clickshop.loja.utils.Paginator;
import com.google.gson.Gson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Api(value = "User Controller")
@RestController
@RequestMapping(value = "/user")
public class UserController {

	private static final Gson gson = new Gson();

	@Autowired
	private UserService userService;

	@ApiOperation(value = "Create a New User")
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody UserResource userReq) {

		log.info("--> Starting / Create User Controller");

		User userEnt = userService.fromResource(userReq);
		userService.create(userEnt);

		log.info("--> Returning / Create User Controller");

		return ResponseEntity.ok().build();
	}

	@ApiOperation(value = "Find User By ID")
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Integer id) {

		log.info("--> Starting / FindById User Controller");

		User userEnt = userService.findById(id);

		log.info("--> Returning / FindById User Controller");

		return ResponseEntity.ok().body(userEnt);
	}

	@ApiOperation(value = "Find All Users")
	@GetMapping
	public ResponseEntity<List<User>> findAll() {

		log.info("--> Starting / FindAll User Controller");

		List<User> userEnts = userService.findAll();

		log.info("--> Returning / FindAll User Controller");

		return ResponseEntity.ok().body(userEnts);
	}

	@ApiOperation(value = "Find Users Page")
	@GetMapping(value = "/page")
	public ResponseEntity<Page<User>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "itemsPerPage", defaultValue = "24") Integer itemsPerPage,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction) {
		log.info("--> Starting / FindPage User Controller");

		Page<User> userEnts = userService.findPage(new Paginator(page, itemsPerPage, orderBy, direction));

		log.info("--> Returning / FindPage User Controller");

		return ResponseEntity.ok().body(userEnts);

	}

	@ApiOperation(value = "Delete User By ID")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {

		log.info("--> Starting / Delete User Controller");

		userService.delete(id);

		log.info("--> Returning / Delete User Controller");

		return ResponseEntity.ok(gson.toJson("Usuario Deletado"));
	}

	@ApiOperation(value = "Update User By ID")
	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@PathVariable Integer id, @RequestBody UserResource userReq) {

		log.info("--> Starting / Update User Controller");

		userReq.setId(id);
		User userEnt = userService.fromResource(userReq);
		User userUpdated = userService.update(userEnt);

		log.info("--> Returning / Update User Controller");

		return ResponseEntity.ok().body(userUpdated);
	}

}
