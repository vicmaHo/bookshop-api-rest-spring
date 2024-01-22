package com.ucoltis.vichernandez.bookshop.controller.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


// con esta clase podremos manejar los errores como objetos 
@Data
@AllArgsConstructor
@Builder // nos permite crear un objeto a partir de asigar valores a sus atributos
public class ErrorMessage {
	private String code;
	private String message;
}
