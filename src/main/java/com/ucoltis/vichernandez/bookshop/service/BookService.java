package com.ucoltis.vichernandez.bookshop.service;

import java.util.List;


import com.ucoltis.vichernandez.bookshop.controller.dto.BookRequest;
import com.ucoltis.vichernandez.bookshop.controller.dto.BookResponse;

public interface BookService {
	
	List<BookResponse> list();

	BookResponse findById(Long id);

	BookResponse create(BookRequest value);

	BookResponse update(Long id, BookRequest value);

	void delete(Long id);
}
