package com.clickshop.loja.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@PrimaryKeyJoinColumn(name="id")
public class Client extends Person{
	private static final long serialVersionUID = 1L;
	
	@Column(unique=true, nullable=false)
	private String instaUsername;

	public Client(Integer id, String name, String instaUsername, String email, Set<String> phoneNumbers, List<Address> addresses) {
		super(id, name, email, phoneNumbers, addresses);
		this.instaUsername = instaUsername;
	}
	

	
	
	
	
	
}
