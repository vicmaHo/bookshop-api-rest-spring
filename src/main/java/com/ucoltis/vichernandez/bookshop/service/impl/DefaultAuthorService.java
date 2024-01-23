package com.ucoltis.vichernandez.bookshop.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ucoltis.vichernandez.bookshop.controller.dto.AuthorRequest;
import com.ucoltis.vichernandez.bookshop.controller.dto.AuthorResponse;
import com.ucoltis.vichernandez.bookshop.controller.exceptions.BadArgumentException;
import com.ucoltis.vichernandez.bookshop.controller.exceptions.NotFoundException;
import com.ucoltis.vichernandez.bookshop.model.entity.Author;
import com.ucoltis.vichernandez.bookshop.model.repository.AuthorRepository;
import com.ucoltis.vichernandez.bookshop.service.AuthorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class DefaultAuthorService implements AuthorService {

	private final AuthorRepository repository;
	private final ModelMapper modelMapper; // este objeto no es gestionado por springboot, por lo tanto no puedo hacer una inyeccion
	// para ello genero el bean para que se configure y pueda usarlo como objeto, ya quedo en BeanConfiguration
	
	
	@Override
	public List<AuthorResponse> list() {
		
		
		var response = repository.findAll().stream()
				.map((value) -> modelMapper.map(value, AuthorResponse.class)) // mapeo el objeto de Author a un AuthorResponse con el modelMapper
				.toList();
		
		if(response.isEmpty()) {
			throw new NotFoundException("001", "No existen autores registrados");
		}
		
		return response;
	}

	@Override
	public AuthorResponse findById(Long id) {
		
		if (id == null || id == 0) {
			throw new BadArgumentException("002", "Parametro invalido");
		}
		
		var author = repository.findById(id)
				.map((element) -> modelMapper.map(element, AuthorResponse.class))
				.orElseThrow(() -> new NotFoundException("001", "No se encontró un elemento con el id: " + id));
	
		return author;
	}

	@Override
	public AuthorResponse create(AuthorRequest value) {
		
		
		
		if (value == null) {
			throw new BadArgumentException("002", "Los datos son obligatorios");
		}
		
		System.out.println(value.toString());
		
		var newAuthor = modelMapper.map(value, Author.class);
		newAuthor = repository.save(newAuthor);
		
		return modelMapper.map(newAuthor, AuthorResponse.class);
	}

	@Override
	public AuthorResponse update(Long id, AuthorRequest value) {
		if (id == null || id == 0) {
			throw new BadArgumentException("002", "El ID es un parametro invalido");
		}
		
		if (value == null) {
			throw new BadArgumentException("002", "Los datos son obligatorios");
		}
		
		var oldAuthor = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("001", "No se encontró un elemento con el id: " + id));
		
		// transformo el autorviejo a uno nuevo
		oldAuthor.setFirstName(value.getFirstName());
		oldAuthor.setLastName(value.getLastName());
		oldAuthor.setBiography(value.getBiography());
		
		
		var newAuthor = repository.save(oldAuthor);
		
		System.out.println(value.toString());
		System.out.println(newAuthor.toString());
		
		return modelMapper.map(newAuthor, AuthorResponse.class);
	}

	@Override
	public void delete(Long id) {
		
		if (id == null || id == 0) {
			throw new BadArgumentException("002", "El ID es un parametro invalido");
		}
		
		repository.findById(id)
				.orElseThrow(() -> new NotFoundException("001", "No se encontró un elemento con el id: " + id));
		

		repository.deleteById(id);
		
	}

}
