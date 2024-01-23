package com.ucoltis.vichernandez.bookshop.service;

import java.util.List;

import com.ucoltis.vichernandez.bookshop.controller.dto.AuthorRequest;
import com.ucoltis.vichernandez.bookshop.controller.dto.AuthorResponse;

public interface AuthorService {
	List<AuthorResponse> list();

	AuthorResponse findById(Long id);

	AuthorResponse create(AuthorRequest value);

	AuthorResponse update(Long id, AuthorRequest value);

	void delete(Long id);
}
