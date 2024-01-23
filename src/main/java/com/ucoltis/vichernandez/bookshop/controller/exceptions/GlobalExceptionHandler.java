package com.ucoltis.vichernandez.bookshop.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// esta es la clase que manejara de forma global las excepciones, si no uso trycatch esta clase es la que manejara el error
@RestControllerAdvice // indico que estara escuchando lo que sucede en la capa de controlador para manejar excepciones
public class GlobalExceptionHandler {

	// Este metodo gestionara todos los errores que ocurran de badargument
	@ExceptionHandler(BadArgumentException.class)	
	public ResponseEntity<ErrorMessage> handleBadArgument(BadArgumentException exception){
		return ResponseEntity.badRequest()
				.body(ErrorMessage.builder()
						.code(exception.getCode())
						.message(exception.getMessage())
						.build());
	}

	
	@ExceptionHandler(NotFoundException.class)	
	public ResponseEntity<ErrorMessage> handleNotFound(NotFoundException exception){
		return ResponseEntity.status(HttpStatus.NOT_FOUND.value())
				.body(ErrorMessage.builder()
						.code(exception.getCode())
						.message(exception.getMessage())
						.build());
	}
	
	// El manejador default para excepciones no especificas
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorMessage> handleDefault(RuntimeException exception){
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE.value())
				.body(ErrorMessage.builder()
						.code("099")
						.message(exception.getMessage())
						.build());
	}
}
