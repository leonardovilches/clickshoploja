package com.clickshop.loja.resources;

import lombok.Data;

@Data
public class ClienteResource {
	
	private Integer id;
	private String name;
	private String instaUsername;
	private String email;
	private String phoneNumber;
	
	public ClienteResource(Integer id, String name, String instaUsername, String email, String phoneNumber) {
		super();
		this.id = id;
		this.name = name;
		this.instaUsername = instaUsername;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	
}
