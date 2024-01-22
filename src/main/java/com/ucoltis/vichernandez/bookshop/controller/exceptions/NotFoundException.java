package com.ucoltis.vichernandez.bookshop.controller.exceptions;

public class NotFoundException extends BaseException{
	
	public NotFoundException(String code, String message) {
		super(code, message);
	}
}
