package me.surreallobster.sampleRest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import me.surreallobster.restfulShapes.AreaResponse;
import me.surreallobster.restfulShapes.PerimeterResponse;
import me.surreallobster.restfulShapes.ShapeRequest;
import me.surreallobster.restfulShapes.ShapeResponse;
import me.surreallobster.restfulShapes.controllers.ShapeController;
import me.surreallobster.restfulShapes.domain.BaseShape;
import me.surreallobster.restfulShapes.domain.Circle;
import me.surreallobster.restfulShapes.domain.Square;
import me.surreallobster.restfulShapes.repository.ShapeRepository;

@RunWith(MockitoJUnitRunner.class)
public class ShapeControllerTest {

	@Mock
	private ShapeRepository shapeRepository;
	@InjectMocks
	private ShapeController shapeController;
	Long id = (long) 1;
	private BaseShape baseSquare;
	private BaseShape baseCircle;
	Square square;
	Circle circle;

	@BeforeEach
	public void init() {
	    MockitoAnnotations.initMocks(this);
	    baseSquare = new BaseShape();
	    baseSquare.setType("square");
	    baseSquare.setValue(3);
	    baseSquare.setId(id);
	    baseCircle = new BaseShape();
	    baseCircle.setType("circle");
	    baseCircle.setValue(3);
	    baseCircle.setId(id);
		square = new Square();
		square.setSide(baseSquare.getValue());
		square.setId(id);
		circle = new Circle();
		circle.setRadius(baseCircle.getValue());
		circle.setId(id);
	}

	@Test
	public void createSquare() {
		ShapeRequest shapeRequest = new ShapeRequest();
		shapeRequest.setType("square");
		shapeRequest.getValues().add(3);
		when(shapeRepository.save(any(BaseShape.class))).thenReturn(baseSquare);
		ResponseEntity<ShapeResponse> entity = shapeController.createShape(shapeRequest);
		assertEquals(square, entity.getBody().getSquares().get(0));

	}

	@Test
	public void createCircle() {
		ShapeRequest shapeRequest = new ShapeRequest();
		shapeRequest.setType("circle");
		shapeRequest.getValues().add(3);
		when(shapeRepository.save(any(BaseShape.class))).thenReturn(baseCircle);
		ResponseEntity<ShapeResponse> entity = shapeController.createShape(shapeRequest);
		assertEquals(circle, entity.getBody().getCircles().get(0));
	}
	
	@Test
	public void deleteEntity() {
		doNothing().when(shapeRepository).deleteById(any(Long.class));
		shapeController.deleteShape(id);
		verify(shapeRepository, times(1)).deleteById(id);
	}
	
	@Test
	public void updateEntity() {
		Optional<BaseShape> optCircle = Optional.of(baseCircle);
		when(shapeRepository.save(any(BaseShape.class))).thenReturn(baseCircle);
		when(shapeRepository.findById(any(Long.class))).thenReturn(optCircle);
		BaseShape updateShape = shapeController.updateShapeById(baseCircle, id);
		verify(shapeRepository, times(1)).save(any(BaseShape.class));
		assertEquals(baseCircle, updateShape);
	}
	
	@Test
	public void getSquare() {
		Optional<BaseShape> optSquare = Optional.of(baseSquare);
		when(shapeRepository.findById(any(Long.class))).thenReturn(optSquare);
		ResponseEntity<ShapeResponse> entity = shapeController.getShapeById(id);
		verify(shapeRepository, times(1)).findById(id);
		assertEquals(square, entity.getBody().getSquares().get(0));
		
	}
	@Test
	public void getCircle() {
		Optional<BaseShape> optCircle = Optional.of(baseCircle);
		when(shapeRepository.findById(any(Long.class))).thenReturn(optCircle);
		ResponseEntity<ShapeResponse> entity = shapeController.getShapeById(id);
		verify(shapeRepository, times(1)).findById(id);
		assertEquals(circle, entity.getBody().getCircles().get(0));
	}
	@Test
	public void getArea() {
		Optional<BaseShape> optCircle = Optional.of(baseCircle);
		when(shapeRepository.findById(any(Long.class))).thenReturn(optCircle);
		ResponseEntity<AreaResponse> entity = shapeController.getArea(id);
		verify(shapeRepository, times(2)).findById(id);
		assertEquals(Circle.calculateArea(circle.getRadius()), entity.getBody().getArea());
	}
	@Test
	public void getPerimeter() {
		Optional<BaseShape> optCircle = Optional.of(baseCircle);
		when(shapeRepository.findById(any(Long.class))).thenReturn(optCircle);
		ResponseEntity<PerimeterResponse> entity = shapeController.getPerimeter(id);
		verify(shapeRepository, times(2)).findById(id);
		assertEquals(Circle.calculatePerimeter(circle.getRadius()), entity.getBody().getPerimeter());
	}
	

}
