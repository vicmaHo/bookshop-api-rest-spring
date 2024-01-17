package com.ucoltis.vichernandez.bookshop.model.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //valor autogenerado
	private Long id;
	
	@Column(nullable = false, length = 150)
	private String title;
	
	@Column(length = 4000)
	private String description;
	
	@Column(nullable = false)
	private Double price;
	
	@Column(nullable = false, length = 20)
	private String isbn;
	
	@Column(nullable = false)
	private Integer pages;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private LocalDate releaseDate;
	
	@Column(length = 155)
	private String image;
	
	@ManyToOne
	private Category category;
	
	@ManyToOne
	private Author author;
	
	
}
