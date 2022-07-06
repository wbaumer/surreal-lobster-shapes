package me.surreallobster.restfulShapes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class RestfulShapesApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(RestfulShapesApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	    return application.sources(RestfulShapesApplication.class);
	}
}
