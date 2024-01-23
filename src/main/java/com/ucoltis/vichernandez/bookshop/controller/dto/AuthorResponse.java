package com.ucoltis.vichernandez.bookshop.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorResponse {

	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String biography;
}
