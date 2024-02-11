package com.ucoltis.vichernandez.bookshop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ucoltis.vichernandez.bookshop.controller.dto.BookRequest;
import com.ucoltis.vichernandez.bookshop.controller.dto.BookResponse;
import com.ucoltis.vichernandez.bookshop.service.BookService;

import lombok.RequiredArgsConstructor;

@RestController // Indico que es un restcontroller
@RequestMapping("api/v1/book") // indico el endpoint -> Controlador que estara escuchando por esta ruta
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class BookController {

	private final BookService service;
	
	@GetMapping
	public List<BookResponse> list() {
		
		return service.list();
	}
	
	@GetMapping("/{id}")
	public BookResponse getById(@PathVariable("id") Long id) {
		
		return service.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public BookResponse create(@RequestBody BookRequest author) {
		
		return service.create(author);
	}
	
	@PutMapping("/{id}")
	public BookResponse update(@PathVariable("id") Long id, @RequestBody BookRequest author) {
		
		return service.update(id, author);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		
		service.delete(id);
	}
	
}
