package com.ucoltis.vichernandez.bookshop.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ucoltis.vichernandez.bookshop.model.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
