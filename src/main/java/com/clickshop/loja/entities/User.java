package com.clickshop.loja.entities;

import java.io.Serializable;
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

import com.clickshop.loja.entities.enums.PermissionLevel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private String cpf;
	
	@Column(unique=true, nullable=false)
	private String email;
	
	private String phoneNumber;
	
	@JsonIgnore
	@Column(nullable=false) 
	private String password;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="permissionLevels")
	private Set<Integer> permissionLevels = new HashSet<>();
	
	public User() {
		addPermissionLevel(PermissionLevel.CLIENT);
	}
	
	public User(Integer id, String name, String cpf, String email, String phoneNumber, String password) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		addPermissionLevel(PermissionLevel.CLIENT);
	}
	
	public Set<PermissionLevel> getpermissionLevels() {
		return permissionLevels.stream().map(i -> PermissionLevel.toEnum(i)).collect(Collectors.toSet());
	}
	
	public void addPermissionLevel(PermissionLevel permissionLevel) {
		permissionLevels.add(permissionLevel.getCod());
	}

}
