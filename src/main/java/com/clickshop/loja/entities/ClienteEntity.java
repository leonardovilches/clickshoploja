package com.clickshop.loja.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class ClienteEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	@Column(unique=true, nullable=false)
	private String instaUsername;
	
	private String email;
	
	private String phoneNumber;
	
	public ClienteEntity() {
		 
	}

	public ClienteEntity(Integer id, String name, String instaUsername, String email, String phoneNumber) {
		super();
		this.id = id;
		this.name = name;
		this.instaUsername = instaUsername;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	
	
}
