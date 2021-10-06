package com.clickshop.loja.resources;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class phoneNumberResource {

	
	@Pattern(regexp = "[\\s]*[0-9]*[1-9]+", message="Formato do numero incorreto")
	@Size(min = 10, max = 12)
	@NotEmpty(message="Novo número necessário")
	private String newPhoneNumber;
	
	public phoneNumberResource(String newPhoneNumber) {
		this.newPhoneNumber = newPhoneNumber;
	}
	
}
