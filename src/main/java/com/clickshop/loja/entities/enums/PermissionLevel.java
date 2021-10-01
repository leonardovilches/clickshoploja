package com.clickshop.loja.entities.enums;

public enum PermissionLevel {

	ADMIN(1, "ROLE_ADMIN"),
	CLIENT(2, "ROLE_CLIENT");
	
	private Integer cod;
	private String description;
	
	private PermissionLevel(Integer cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescription() {
		return description;
	}
	
	public static PermissionLevel toEnum(Integer cod) {
		if(cod == null) return null;
		
		for(PermissionLevel i : PermissionLevel.values()) {
			if(cod.equals(i.getCod())) return i;
		}
		
		throw new IllegalArgumentException("Id Inválido: " + cod);
	}

}