package com.ucoltis.vichernandez.bookshop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Indico que es un restcontroller
@RequestMapping("api/v1/author") // indico el endpoint -> Controlador que estara escuchando por esta ruta
public class AuthorController {

	public void list() {
		
	}
	
	public void getById(Long id) {
		
	}
	
	public void create(Object author) {
		
	}
	
	public void update(Long id, Object author) {
		
	}
	
	public void delete(Long id) {
		
	}
}
