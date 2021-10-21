package com.clickshop.loja.controllers.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.clickshop.loja.services.exceptions.ObjectAlreadyRegistered;
import com.clickshop.loja.services.exceptions.ObjectNotFoundException;
import com.clickshop.loja.services.exceptions.ParseError;
import com.clickshop.loja.services.exceptions.ValidationError;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {

		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);

	}

	@ExceptionHandler(ObjectAlreadyRegistered.class)
	public ResponseEntity<StandardError> objectAlreadyRegistered(ObjectAlreadyRegistered e, HttpServletRequest request) {
		
		StandardError err = new StandardError(HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(err);
	}
	
	@ExceptionHandler(ParseError.class)
	public ResponseEntity<StandardError> parseError(ParseError e, HttpServletRequest request) {

		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);

	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> validationError(MethodArgumentNotValidException e, HttpServletRequest request) {
		
		ValidationError err = new ValidationError(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de Validação", System.currentTimeMillis());
		
		for(FieldError i : e.getBindingResult().getFieldErrors()) {
			err.addError(i.getField(), i.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
	}
	 
	 
}
