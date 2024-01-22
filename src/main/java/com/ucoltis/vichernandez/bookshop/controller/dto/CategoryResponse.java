package com.ucoltis.vichernandez.bookshop.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// clase de transferencia de datos para las respuestas
@Data 
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
	
	private Long id;
	private String name;
}
