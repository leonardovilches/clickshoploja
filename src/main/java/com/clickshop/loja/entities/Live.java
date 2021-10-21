package com.clickshop.loja.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.clickshop.loja.entities.enums.LiveStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Live implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private Date date;
	
	@Column(nullable = false)
	private double commission;
	
	@Column(nullable = false)
	private double cardTax;
	
	@Column(nullable = false)
	private double shippingTax;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;
	
	private Integer liveStatus;
	
	public Live() {
		liveStatus = 3;
	}
	
	public Live(Integer id, Date date, double commission, double cardTax, double shippingTax, Person person) {
		super();
		this.id = id;
		this.date = date;
		this.commission = commission;
		this.cardTax = cardTax;
		this.shippingTax = shippingTax;
		this.person = person;
		liveStatus = 3;
	}
	
	public LiveStatus getLiveStatus() {
		return LiveStatus.toEnum(liveStatus);
	}
	
}
