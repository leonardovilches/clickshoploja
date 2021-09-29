package com.clickshop.loja.resources;

import javax.validation.constraints.Email;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientResource {
	
	private Integer id;
	private String name;
	private String instaUsername;
	@Email(message = "Usuario Já registrado")
	private String email;
	private String phoneNumber;
	
	public ClientResource(Integer id, String name, String instaUsername, String email, String phoneNumber) {
		super();
		this.id = id;
		this.name = name;
		this.instaUsername = instaUsername;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	
}
