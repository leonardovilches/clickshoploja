package com.clickshop.loja.services.exceptions;

public class ObjectAlreadyRegistered extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public ObjectAlreadyRegistered(String msg) {
		super(msg);
	}
	
	public ObjectAlreadyRegistered(String msg, Throwable cause) {
		super(msg, cause);
	}

}
