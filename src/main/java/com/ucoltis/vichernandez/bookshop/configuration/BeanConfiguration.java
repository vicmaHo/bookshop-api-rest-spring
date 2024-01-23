package com.ucoltis.vichernandez.bookshop.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//esta sera una clase de configuracion
@Configuration
public class BeanConfiguration {

	// de esta forma indico que este sera un objeto que formara parte de la configuracion y puedo usarlo como dependencia
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
}
