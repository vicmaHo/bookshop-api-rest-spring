package com.ucoltis.vichernandez.bookshop.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ucoltis.vichernandez.bookshop.controller.dto.BookRequest;
import com.ucoltis.vichernandez.bookshop.controller.dto.BookResponse;
import com.ucoltis.vichernandez.bookshop.controller.exceptions.BadArgumentException;
import com.ucoltis.vichernandez.bookshop.controller.exceptions.NotFoundException;
import com.ucoltis.vichernandez.bookshop.model.entity.Book;
import com.ucoltis.vichernandez.bookshop.model.repository.BookRepository;
import com.ucoltis.vichernandez.bookshop.service.BookService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DefaultBookService implements BookService {
	
	private final BookRepository repository;
	private final ModelMapper modelMapper;
	
	@Override
	public List<BookResponse> list() {
		var response = repository.findAll().stream()
				.map((value) -> modelMapper.map(value, BookResponse.class)) // mapeo el objeto de Author a un AuthorResponse con el modelMapper
				.toList();
		
		if(response.isEmpty()) {
			throw new NotFoundException("001", "No existen Libros registrados");
		}
		
		return response;
	}

	@Override
	public BookResponse findById(Long id) {
		
		if (id == null || id == 0) {
			throw new BadArgumentException("002", "Parametro invalido");
		}
		
		var book = repository.findById(id)
				.map((element) -> modelMapper.map(element, BookResponse.class))
				.orElseThrow(() -> new NotFoundException("001", "No se encontró un elemento con el id: " + id));
	
		return book;
	}

	@Override
	public BookResponse create(BookRequest value) {
		
		if (value == null) {
			throw new BadArgumentException("002", "Los datos son obligatorios");
		}
		
		var newBook = modelMapper.map(value, Book.class); // el id al mapear queda nulo
		System.out.println(newBook.toString());
		newBook = repository.save(newBook);
		
		return modelMapper.map(newBook, BookResponse.class);
	}

	@Override
	public BookResponse update(Long id, BookRequest value) {
		
		if (id == null || id == 0) {
			throw new BadArgumentException("002", "El ID es un parametro invalido");
		}
		
		if (value == null) {
			throw new BadArgumentException("002", "Los datos son obligatorios");
		}
		
		var oldBook = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("001", "No se encontró un elemento con el id: " + id));
		
		// transformo el libro viejo a uno nuevo
		oldBook.setAuthor(value.getAuthor());
		oldBook.setCategory(value.getCategory());
		oldBook.setDescription(value.getDescription());
		oldBook.setImage(value.getImage());
		oldBook.setIsbn(value.getIsbn());
		oldBook.setPages(value.getPages());
		oldBook.setPrice(value.getPrice());
		oldBook.setReleaseDate(value.getReleaseDate());
		oldBook.setTitle(value.getTitle());
		
		
		var newBook = repository.save(oldBook);
		
		return modelMapper.map(newBook, BookResponse.class);
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
