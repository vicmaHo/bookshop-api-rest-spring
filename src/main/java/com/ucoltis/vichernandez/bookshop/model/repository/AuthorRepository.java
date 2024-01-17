package com.ucoltis.vichernandez.bookshop.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ucoltis.vichernandez.bookshop.model.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
