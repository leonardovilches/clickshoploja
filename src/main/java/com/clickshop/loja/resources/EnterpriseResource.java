package com.clickshop.loja.resources;


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
public class EnterpriseResource {
	
	private Integer id;
	private String name;
	@NotEmpty(message = "Usuario deve ser Registrado")
	private String enterpriseName;
	@Email(message = "Email Invalido")
	private String email;
	private Set<String> phoneNumbers;
	private Set<Address> addresses;
	
	public EnterpriseResource(Integer id, String name, String enterpriseName, String email, Set<String> phoneNumbers, Set<Address> addresses) {
		super();
		this.id = id;
		this.name = name;
		this.enterpriseName = enterpriseName;
		this.email = email;
		this.phoneNumbers = phoneNumbers;
		this.addresses = addresses;
	}
	
}
