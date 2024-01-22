package com.ucoltis.vichernandez.bookshop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ucoltis.vichernandez.bookshop.controller.dto.CategoryRequest;
import com.ucoltis.vichernandez.bookshop.controller.dto.CategoryResponse;
import com.ucoltis.vichernandez.bookshop.model.entity.Category;
import com.ucoltis.vichernandez.bookshop.model.repository.CategoryRepository;
import com.ucoltis.vichernandez.bookshop.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DefaultCategoryService implements CategoryService{

	private final CategoryRepository categoryRepository;
	
	@Override
	public List<CategoryResponse> list() {
		
		var categories = categoryRepository.findAll();
		
		//Mapeo las entidades de la base de datos a objetos de datos
//		List<CategoryResponse> response = new ArrayList<CategoryResponse>();
//		
//		//cada uno de los elementos de la base de datos se convierte en una respuesta y se le asignan los datos
//		for (var category : categories) {
//			var data = new CategoryResponse();
//			data.setId(category.getId());
//			data.setName(category.getName());
//			
//			response.add(data);
//		} 
		
		// uso de una funcion lambda para recorrer la lista y transformarla con map
		var response = categoryRepository.findAll().stream()
				.map((category) -> new CategoryResponse(category.getId(), category.getName()))
				.toList();
		
		return response;
	}

	@Override
	public CategoryResponse findById(Long id) {
		
		var category = categoryRepository.findById(id)
				.map((element) -> new CategoryResponse(element.getId(), element.getName()))
				.orElse(new CategoryResponse()); // es un elemento optional con la finalidad de manejar valores nulos, lo manejo de esta forma
		// intento converttir el objeto y si no devuelvo la categoria vacia
	
		
		return category;
		
	}

	@Override
	public CategoryResponse create(CategoryRequest category) {
		
		var newCategory = categoryRepository.save(new Category(null, category.getName())); // al ser autoincrement mando id null
		
		return new CategoryResponse(newCategory.getId(), newCategory.getName());
	}

	@Override
	public CategoryResponse update(Long id, CategoryRequest category) {
		
		var oldCategory = categoryRepository.findById(id);
		
		if (oldCategory.isPresent()) {
			var newCategory = oldCategory.get();
			newCategory.setName(category.getName());
			newCategory = categoryRepository.save(newCategory);
			
			return new CategoryResponse(newCategory.getId(), newCategory.getName());
		}
		
		return new CategoryResponse();
	}

	@Override
	public void delete(Long id) {
		var oldCategory = categoryRepository.findById(id);
		
		if (oldCategory.isPresent()) {
			categoryRepository.deleteById(id);
		}
	}

}
