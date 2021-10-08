package com.clickshop.loja.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@EqualsAndHashCode(exclude = {"addresses", "phoneNumbers"})
@NoArgsConstructor
public class Client implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	@Column(unique=true, nullable=false)
	private String instaUsername;
	
	@Column(unique=true, nullable=false)
	private String email;
	
	@ElementCollection
	@CollectionTable(name="phoneNumber")
	private Set<String> phoneNumbers = new HashSet<>();
	
	@OneToMany(mappedBy = "client")
	private Set<Address> addresses = new HashSet<>(); 

	public Client(Integer id, String name, String instaUsername, String email, String phoneNumber, Set<Address> addresses) {
		super();
		this.id = id;
		this.name = name;
		this.instaUsername = instaUsername;
		this.email = email;
		phoneNumbers.add(phoneNumber);
		this.addresses = addresses;
	}
	
	
	
	
}
