package com.user.notesapi.appconfig;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
	
	@Bean
	public ModelMapper getmodelmapper()
	{
		return new ModelMapper();
	}
}
