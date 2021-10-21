package com.clickshop.loja.resources;


import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.clickshop.loja.entities.Address;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientResource {
	
	private Integer id;
	private String name;
	@NotEmpty(message = "Usuario deve ser Registrado")
	private String instaUsername;
	@Email(message = "Email Invalido")
	private String email;
	private Set<String> phoneNumbers;
	private List<Address> addresses;
	
	public ClientResource(Integer id, String name, String instaUsername, String email, Set<String> phoneNumbers, List<Address> addresses) {
		super();
		this.id = id;
		this.name = name;
		this.instaUsername = instaUsername;
		this.email = email;
		this.phoneNumbers = phoneNumbers;
		this.addresses = addresses;
	}
	
}
