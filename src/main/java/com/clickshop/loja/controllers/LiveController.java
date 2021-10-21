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

import com.clickshop.loja.entities.Live;
import com.clickshop.loja.entities.enums.LiveStatus;
import com.clickshop.loja.resources.LiveResource;
import com.clickshop.loja.services.LiveService;
import com.clickshop.loja.utils.Paginator;
import com.google.gson.Gson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Api(value = "Live Controller")
@RestController
@RequestMapping(value = "/live")
public class LiveController {

	private static final Gson gson = new Gson();

	@Autowired
	private LiveService liveService;

	@ApiOperation(value = "Create a New Live")
	@PostMapping
	public ResponseEntity<Void> create(@Valid @RequestBody LiveResource liveReq) {

		log.info("--> Starting / Create Live Controller");

		Live liveEnt = liveService.fromResource(liveReq);
		liveService.create(liveEnt);

		log.info("--> Returning / Create Live Controller");

		return ResponseEntity.ok().build();
	}

	@ApiOperation(value = "Find Live By ID")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Live> findById(@PathVariable Integer id) {

		log.info("--> Starting / FindById Live Controller");

		Live liveEnt = liveService.findById(id);

		log.info("--> Returning / FindById Live Controller");

		return ResponseEntity.ok().body(liveEnt);
	}

	@ApiOperation(value = "Find All Lives")
	@GetMapping
	public ResponseEntity<List<Live>> findAll() {

		log.info("--> Starting / FindAll Live Controller");

		List<Live> liveEnts = liveService.findAll();

		log.info("--> Returning / FindAll Live Controller");

		return ResponseEntity.ok().body(liveEnts);
	}

	@ApiOperation(value = "Find Lives Page")
	@GetMapping(value = "/page")
	public ResponseEntity<Page<Live>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "itemsPerPage", defaultValue = "24") Integer itemsPerPage,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction) {
		log.info("--> Starting / FindPage Live Controller");

		Page<Live> liveEnts = liveService.findPage(new Paginator(page, itemsPerPage, orderBy, direction));

		log.info("--> Returning / FindPage Live Controller");

		return ResponseEntity.ok().body(liveEnts);

	}

	@ApiOperation(value = "Delete Live By ID")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {

		log.info("--> Starting / Delete Live Controller");

		liveService.delete(id);

		log.info("--> Returning / Delete Live Controller");

		return ResponseEntity.ok(gson.toJson("Usuario Deletado"));
	}

	@ApiOperation(value = "Update Live By ID")
	@PutMapping(value = "/{id}")
	public ResponseEntity<Live> update(@PathVariable Integer id, @RequestBody LiveResource liveReq) {

		log.info("--> Starting / Update Live Controller");

		liveReq.setId(id);
		Live liveEnt = liveService.fromResource(liveReq);
		Live liveUpdated = liveService.update(liveEnt);

		log.info("--> Returning / Update Live Controller");

		return ResponseEntity.ok().body(liveUpdated);
	}
	
	@ApiOperation(value = "Open Live by ID")
	@PutMapping(value = "start/{id}")
	public ResponseEntity<Live> startLive(@PathVariable Integer id) {

		LiveStatus liveStatus = liveService.findById(id).getLiveStatus();
		
		Live liveUpdated = null;
		
		if(!(liveStatus == LiveStatus.OPENED)) {
			liveUpdated = liveService.startLive(id);
		}
	
		return ResponseEntity.ok().body(liveUpdated);
	}
	
	@ApiOperation(value = "Close Live by ID")
	@PutMapping(value = "close/{id}")
	public ResponseEntity<Live> closeLive(@PathVariable Integer id) {

		LiveStatus liveStatus = liveService.findById(id).getLiveStatus();
		
		Live liveUpdated = null;
		
		if(!(liveStatus == LiveStatus.CLOSED || liveStatus == LiveStatus.PENDING)) {
			liveUpdated = liveService.closeLive(id);
		}
	
		return ResponseEntity.ok().body(liveUpdated);
	}
}
