package com.clickshop.loja.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@PrimaryKeyJoinColumn(name="id")
public class Enterprise extends Person{
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "enterprise")
	private List<Live> live = new ArrayList<>();
	
	private String enterpriseName;
	
	public Enterprise(Integer id, String name, String enterpriseName, String email, Set<String> phoneNumbers, List<Address> addresses) {
		super(id, name, email, phoneNumbers, addresses);
		this.enterpriseName = enterpriseName;
	}
}
