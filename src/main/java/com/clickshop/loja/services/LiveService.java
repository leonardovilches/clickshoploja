package com.clickshop.loja.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clickshop.loja.entities.Enterprise;
import com.clickshop.loja.entities.Live;
import com.clickshop.loja.repositories.LiveRepository;
import com.clickshop.loja.resources.LiveResource;
import com.clickshop.loja.services.exceptions.ObjectNotFoundException;
import com.clickshop.loja.services.exceptions.ParseError;
import com.clickshop.loja.utils.Paginator;

@Service
public class LiveService {

	@Autowired
	private LiveRepository liveRepository;
	
	@Autowired
	private EnterpriseService enterpriseService;
	
	@Transactional
	public Live create(Live liveObj) {
		liveObj.setId(null);
			
			return liveRepository.save(liveObj);
	}
	
	public Live findById(Integer id) {

		Optional<Live> live = liveRepository.findById(id);
			return live.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto não encontrado! id: " + id + "; Tipo: " + Live.class.getName()));

	}
	
	public List<Live> findAll() {
		return liveRepository.findAll();
	}
	
	public Page<Live> findPage(Paginator paginator) {
		
		PageRequest pageRequest = PageRequest.of(
				paginator.getPageNumber(), paginator.getItemsPerPage(), Direction.valueOf(paginator.getDirection()), paginator.getOrderBy());
		
		return liveRepository.findAll(pageRequest);
	}
	
	public void delete(Integer id) {
		findById(id);
		liveRepository.deleteById(id);
	}
	
	public Live update(Live live) {
		findById(live.getId());
			return liveRepository.save(live);
		
	}
	
	public Live startLive(Integer id) {
		Live live = findById(id);
		
		live.setLiveStatus(2);
		
		return liveRepository.save(live);
	}
	
	public Live closeLive(Integer id) {
		Live live = findById(id);
		
		live.setLiveStatus(1);
		
		return liveRepository.save(live);
	}

	public Live fromResource(LiveResource liveResou) {
		
		Date date;
		try {
			date = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss").parse(liveResou.getDate());
		} catch (ParseException e) {
			throw new ParseError("Formato de Data invalido - Formato Correto: dd/MM/yyyy-HH:mm:ss Tipo: " + Live.class.getName());
		}
		
		Enterprise pri;
		
		if(liveResou.getEnterpriseId() == null) {
			pri = null;
		}
		else {
			pri = enterpriseService.findById(liveResou.getEnterpriseId());
		}
		
		return new Live(liveResou.getId(), date, liveResou.getCommission(), liveResou.getCardTax(),
				liveResou.getShippingTax(), pri);
	}
}
