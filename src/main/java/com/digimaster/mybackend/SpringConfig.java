package com.digimaster.mybackend;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringConfig implements WebMvcConfigurer{
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		//pathnya ini harus pake file: trus akhirannya pake / jangan lupa ne ehehhee
		registry.addResourceHandler("/images/**").addResourceLocations("file:/Users/seraphinatatiana/Documents/Backend/Images/");
		registry.addResourceHandler("/images/**").addResourceLocations("file:/Users/seraphinatatiana/Documents/mybackend/images/");
	}

}
