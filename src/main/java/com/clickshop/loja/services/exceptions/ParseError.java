package com.clickshop.loja.services.exceptions;

public class ParseError extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ParseError(String msg) {
		super(msg);
	}
	
	public ParseError(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
