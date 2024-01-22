package com.ucoltis.vichernandez.bookshop.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


// Mapeamos el modelo de la base de datos a las entidades

@Entity
@Table(name = "my_category") // nombre de la tabla en base de datos
@NoArgsConstructor // con una anotacion lombok indico la creacion de un constructor sin argumentos
@AllArgsConstructor
@Data
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //valor autogenerado
	private Long id;
	
	@Column(length = 100) // Indico el tamaño de la cadena
	private String name;
	
	

}
