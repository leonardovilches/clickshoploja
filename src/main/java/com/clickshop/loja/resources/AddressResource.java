package com.clickshop.loja.resources;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressResource {

	private Integer id;
	@NotEmpty(message = "Cidade deve ser preenchida")
	private String city;
	@NotEmpty(message = "Bairro deve ser preenchida")
	private String district;
	@NotEmpty(message = "Rua deve ser preenchida")
	private String street;
	private String complement;
	@NotNull(message = "Numero deve ser preenchida")
	private Integer number;
	
	public AddressResource(Integer id, String city, String district, String street, String complement, Integer number) {
		this.id = id;
		this.city = city;
		this.district = district;
		this.street = street;
		this.complement = complement;
		this.number = number;
	}
	
}
