package com.ucoltis.vichernandez.bookshop.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ucoltis.vichernandez.bookshop.controller.dto.BookRequest;
import com.ucoltis.vichernandez.bookshop.controller.dto.BookResponse;
import com.ucoltis.vichernandez.bookshop.controller.exceptions.BadArgumentException;
import com.ucoltis.vichernandez.bookshop.controller.exceptions.NotFoundException;
import com.ucoltis.vichernandez.bookshop.model.entity.Book;
import com.ucoltis.vichernandez.bookshop.model.repository.AuthorRepository;
import com.ucoltis.vichernandez.bookshop.model.repository.BookRepository;
import com.ucoltis.vichernandez.bookshop.model.repository.CategoryRepository;
import com.ucoltis.vichernandez.bookshop.service.BookService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DefaultBookService implements BookService {
	
	private final BookRepository bookRepository;
	private final AuthorRepository authorRepository;
	private final CategoryRepository categoryRepository;
	
	private final ModelMapper modelMapper;
	
	@Override
	public List<BookResponse> list() {
		var response = bookRepository.findAll().stream()
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
		
		var book = bookRepository.findById(id)
				.map((element) -> modelMapper.map(element, BookResponse.class))
				.orElseThrow(() -> new NotFoundException("001", "No se encontró un elemento con el id: " + id));
	
		return book;
	}

	@Override
	public BookResponse create(BookRequest value) {
		
		if (value == null) {
			throw new BadArgumentException("002", "Los datos son obligatorios");
		}
		
		
		var author = authorRepository.findById(value.getAuthor()).get();
		var category = categoryRepository.findById(value.getCategory()).get();
		
		
		var newBook = new Book();
		newBook.setTitle(value.getTitle());
		newBook.setDescription(value.getDescription());
		newBook.setPrice(value.getPrice());
		newBook.setIsbn(value.getIsbn());
		newBook.setPages(value.getPages());
		newBook.setReleaseDate(value.getReleaseDate());
		newBook.setImage(value.getImage());
		newBook.setAuthor(author);
		newBook.setCategory(category);
		
		newBook = bookRepository.save(newBook);
		
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
		
		var oldBook = bookRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("001", "No se encontró un elemento con el id: " + id));
		
		var author = authorRepository.findById(value.getAuthor()).get();
		var category = categoryRepository.findById(value.getCategory()).get();
		
		
		// transformo el libro viejo a uno nuevo
		oldBook.setAuthor(author);
		oldBook.setCategory(category);
		oldBook.setDescription(value.getDescription());
		oldBook.setImage(value.getImage());
		oldBook.setIsbn(value.getIsbn());
		oldBook.setPages(value.getPages());
		oldBook.setPrice(value.getPrice());
		oldBook.setReleaseDate(value.getReleaseDate());
		oldBook.setTitle(value.getTitle());
		
		
		var newBook = bookRepository.save(oldBook);
		
		return modelMapper.map(newBook, BookResponse.class);
	}

	@Override
	public void delete(Long id) {
		
		if (id == null || id == 0) {
			throw new BadArgumentException("002", "El ID es un parametro invalido");
		}
		
		bookRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("001", "No se encontró un elemento con el id: " + id));
		

		bookRepository.deleteById(id);
		
	}

}
