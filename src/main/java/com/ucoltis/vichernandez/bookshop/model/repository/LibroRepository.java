package com.ucoltis.vichernandez.bookshop.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ucoltis.vichernandez.bookshop.model.entity.Book;

public interface LibroRepository extends JpaRepository<Book, Long> {

}
