package com.clickshop.loja.entities.enums;

public enum LiveStatus {

	CLOSED(1, "LIVE_CLOSED"),
	OPENED(2, "LIVE_OPENED"),
	PENDING(3, "LIVE_PENDING");

	private Integer cod;
	private String description;
	
	private LiveStatus(Integer cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescription() {
		return description;
	}
	
	public static LiveStatus toEnum(Integer cod) {
		if(cod == null) return null;
		
		for(LiveStatus i : LiveStatus.values()) {
			if(cod.equals(i.getCod())) return i;
		}
		
		throw new IllegalArgumentException("Id Inválido: " + cod);
	}
}
