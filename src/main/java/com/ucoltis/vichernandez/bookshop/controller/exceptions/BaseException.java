package com.ucoltis.vichernandez.bookshop.controller.exceptions;

import lombok.Getter;

@Getter
// esta clase sera la excepcion base de la aplicacion
public abstract class BaseException extends RuntimeException{
	
	private final String code; //agregaremos codigos a gusto para identificar excepciones dentro de la aplicacion
	private final String message;
	
	public BaseException(String code, String message) {
		super(message);
		this.code = code;
		this.message = message;
		
	}
	
}
