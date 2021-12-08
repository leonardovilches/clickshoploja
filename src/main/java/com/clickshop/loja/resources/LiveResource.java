package com.clickshop.loja.resources;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LiveResource implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotEmpty(message = "Data deve ser Registrado")
	private String date;
	@NotNull(message = "Comissão deve ser Registrado")
	private double commission;
	@NotNull(message = "Taxa do Cartão deve ser Registrado")
	private double cardTax;
	@NotNull(message = "Taxa de Entrega deve ser Registrado")
	private double shippingTax;
	private Integer enterpriseId;
	
}
