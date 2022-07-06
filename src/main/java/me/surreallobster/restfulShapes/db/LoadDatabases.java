package me.surreallobster.restfulShapes.db;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import me.surreallobster.restfulShapes.domain.BaseShape;
import me.surreallobster.restfulShapes.domain.Circle;
import me.surreallobster.restfulShapes.repository.ShapeRepository;

@Configuration
public class LoadDatabases {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabases.class);

	@Bean
	CommandLineRunner initDatabase(ShapeRepository shapeRepository) {
		Integer circle1radius = 2;
		Double circle1area = Circle.calculateArea(circle1radius);
		Double circle1perimeter = Circle.calculatePerimeter(circle1radius);
		Integer circle2radius = 3;
		Double circle2area = Circle.calculateArea(circle2radius);
		Double circle2perimeter = Circle.calculatePerimeter(circle2radius);

		return args -> {
			log.info("Loading ShapeRepository with radius: " + circle1radius + ", area: " + circle1area
					+ ", perimeter: " + circle1perimeter);
			BaseShape baseShape = new BaseShape();
			baseShape.setType("circle");
			baseShape.setValue(circle1radius);
			baseShape.setArea(circle1area);
			baseShape.setPerimeter(circle1perimeter);
			shapeRepository.save(baseShape);
			log.info("Loading ShapeRepository with radius: " + circle2radius + ", area: " + circle2area
					+ ", perimeter: " + circle2perimeter);
			baseShape = new BaseShape();
			baseShape.setType("circle");
			baseShape.setValue(circle2radius);
			baseShape.setArea(circle2area);
			baseShape.setPerimeter(circle2perimeter);
			shapeRepository.save(baseShape);
		};

	}
}
