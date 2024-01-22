package com.ucoltis.vichernandez.bookshop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ucoltis.vichernandez.bookshop.controller.dto.CategoryRequest;
import com.ucoltis.vichernandez.bookshop.controller.dto.CategoryResponse;
import com.ucoltis.vichernandez.bookshop.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController // Indico que es un restcontroller
@RequestMapping("api/v1/category") // indico el endpoint -> Controlador que estara escuchando por esta ruta
@RequiredArgsConstructor // busca los atributos final, para crear un constructor con estos atributos
public class CategoryController {

	private final CategoryService categoryService; // la idea en este caso sera tener las dependencias en un constructor para la inyeccion de dependencias
	
	@GetMapping // como es la pagina de inicio puedo dejarlo sin parametro 
	public List<CategoryResponse> list() {
		
		return categoryService.list();	
	}
	
	@GetMapping("/{id}") // parametro por url
	public CategoryResponse getById(@PathVariable("id") Long id) {
		
		return categoryService.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED) // indico que el estado sera created 201, que es el estado para creado con exito
	public CategoryResponse create(@RequestBody CategoryRequest category) {
		
		return categoryService.create(category);
	}
	
	@PutMapping("/{id}")
	public CategoryResponse update(@PathVariable Long id, @RequestBody CategoryRequest category) { // si la variable tiene el mismo nombre puedo dejarala asi en el pathvariable
		
		return categoryService.update(id, category);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		categoryService.delete(id);
	}
	
}
