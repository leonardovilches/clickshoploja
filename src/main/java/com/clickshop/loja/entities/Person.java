package com.clickshop.loja.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@EqualsAndHashCode(exclude = {"addresses", "phoneNumbers"})
@NoArgsConstructor
@Inheritance(strategy=InheritanceType.JOINED)
public class Person implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	@Column(unique=true, nullable=false)
	private String email;
	
	@ElementCollection
	@CollectionTable(name="phoneNumber")
	private Set<String> phoneNumbers = new HashSet<>();
	
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Address> addresses = new ArrayList<>(); 
	
	public Person(Integer id, String name, String email, Set<String> phoneNumbers, List<Address> addresses) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phoneNumbers = phoneNumbers;
		this.addresses = addresses;
	}
}
