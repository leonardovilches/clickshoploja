package com.clickshop.loja.resources;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResource implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	@CPF(message="CPF Invalido")
	private String cpf;
	private String phoneNumber;
	@Email(message="Email Invalido")
	private String email;
	@NotEmpty(message="Senha deve ser Registrado")
	private String password;
	
	
}
