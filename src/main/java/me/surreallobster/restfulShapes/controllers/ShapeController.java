package me.surreallobster.restfulShapes.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import me.surreallobster.restfulShapes.ShapeResponse;
import me.surreallobster.restfulShapes.AreaResponse;
import me.surreallobster.restfulShapes.PerimeterResponse;
import me.surreallobster.restfulShapes.ShapeRequest;
import me.surreallobster.restfulShapes.domain.BaseShape;
import me.surreallobster.restfulShapes.domain.Circle;
import me.surreallobster.restfulShapes.domain.Square;
import me.surreallobster.restfulShapes.repository.ShapeRepository;

@RestController
@Component
public class ShapeController {

	String URN_PREFIX = "urn:me.surreallobster:shapes:id:";
	String CIRCLE_TYPE = "circle";
	String SQUARE_TYPE = "square";

	private final ShapeRepository shapeRepository;

	public ShapeController(ShapeRepository shapeRepository) {
		this.shapeRepository = shapeRepository;
	}

	@GetMapping("/shapes")
	public ResponseEntity<ShapeResponse> getAllShapes() {
		List<BaseShape> baseShapes = shapeRepository.findAll();
		ShapeResponse shapeResponse = new ShapeResponse();
		for (BaseShape baseShape : baseShapes) {
			if (baseShape.getType().equals("circle"))
				shapeResponse.addCircle(adaptShapeToCircle(baseShape));
			else if (baseShape.getType().equals("square"))
				shapeResponse.addSquare(adaptShapeToSquare(baseShape));
		}
		return ResponseEntity.ok().body(shapeResponse);
	}

	@GetMapping("/shapes/{id}")
	public ResponseEntity<ShapeResponse> getShapeById(@PathVariable Long id) throws ShapeNotFoundException {
		BaseShape baseShape = getShape(id);
		ShapeResponse response = new ShapeResponse();
		if (baseShape.getType().equals("circle")) {
			response.addCircle(adaptShapeToCircle(baseShape));
		} else {
			response.addSquare(adaptShapeToSquare(baseShape));
		}
		return ResponseEntity.ok().body(response);
	}

	@PostMapping("/shapes")
	public ResponseEntity<ShapeResponse> createShape(@RequestBody ShapeRequest request) {
		return ResponseEntity.ok().body(createNewShapeFromRequest(request));
	}

	@GetMapping("/shapes/area/{id}")
	public ResponseEntity<AreaResponse> getArea(@PathVariable Long id) throws ShapeNotFoundException {
		BaseShape baseShape = getShape(id);
		AreaResponse areaResponse = new AreaResponse();
		if (baseShape.getArea() == null) {
			if (baseShape.getType().equals(CIRCLE_TYPE)) {
				baseShape.setArea(Circle.calculateArea(baseShape.getValue()));
			} else if (baseShape.getType().equals(SQUARE_TYPE)) {
				baseShape.setArea(Square.calculateArea(baseShape.getValue()));
			}
			updateShape(baseShape, id);
		}
		areaResponse.setArea(baseShape.getArea());
		areaResponse.setId(baseShape.getId());
		areaResponse.setURN(buildUrn(baseShape.getId()));

		return ResponseEntity.ok().body(areaResponse);

	}

	@GetMapping("/shapes/perimeter/{id}")
	public ResponseEntity<PerimeterResponse> getPerimeter(@PathVariable Long id) throws ShapeNotFoundException {
		BaseShape baseShape = getShape(id);
		PerimeterResponse perimeterResponse = new PerimeterResponse();
		if (baseShape.getPerimeter() == null) {
			if (baseShape.getType().equals(CIRCLE_TYPE)) {
				baseShape.setPerimeter(Circle.calculatePerimeter(baseShape.getValue()));
			} else if (baseShape.getType().equals(SQUARE_TYPE)) {
				baseShape.setPerimeter(Square.calculatePerimeter(baseShape.getValue()));
			}
			updateShape(baseShape, id);
		}
		perimeterResponse.setPerimeter(baseShape.getPerimeter());
		perimeterResponse.setId(baseShape.getId());
		perimeterResponse.setURN(buildUrn(baseShape.getId()));

		return ResponseEntity.ok().body(perimeterResponse);

	}

	@PutMapping("/shapes/{id}")
	public BaseShape updateShapeById(@RequestBody BaseShape newShape, @PathVariable Long id) {
		return updateShape(newShape, id);

	}

	@DeleteMapping("/shapes/{id}")
	public void deleteShape(@PathVariable Long id) {
		shapeRepository.deleteById(id);
	}

	private BaseShape newShape(BaseShape shape) {
		return shapeRepository.save(shape);
	}

	private BaseShape getShape(Long id) throws ShapeNotFoundException {
		Optional<BaseShape> baseShape = shapeRepository.findById(id);
		if (baseShape.isPresent()) {
			return baseShape.get();
		}

		else
			throw new ShapeNotFoundException(id);
	}

	private Circle adaptShapeToCircle(BaseShape baseShape) {
		return new Circle(baseShape.getId(), baseShape.getValue(), baseShape.getArea(), baseShape.getPerimeter(),
				buildUrn(baseShape.getId()));

	}

	private Square adaptShapeToSquare(BaseShape baseShape) {
		return new Square(baseShape.getId(), baseShape.getValue(), baseShape.getArea(), baseShape.getPerimeter(),
				buildUrn(baseShape.getId()));

	}

	private ShapeResponse createNewShapeFromRequest(ShapeRequest request) {
		ShapeResponse shapeResponse = new ShapeResponse();
		BaseShape baseShape = new BaseShape();
		if (request.getType().equals("circle")) {
			baseShape.setValue(request.getValues().get(0));
			baseShape.setType(request.getType());
			baseShape = newShape(baseShape);
			shapeResponse.addCircle(adaptShapeToCircle(baseShape));
		}
		if (request.getType().equals("square")) {
			baseShape.setValue(request.getValues().get(0));
			baseShape.setType(request.getType());
			baseShape = newShape(baseShape);
			shapeResponse.addSquare(adaptShapeToSquare(baseShape));
		}
		return shapeResponse;
	}

	private BaseShape updateShape(BaseShape newShape, Long id) {
		BaseShape oldShape = getShape(id);
		oldShape.setType(newShape.getType());
		oldShape.setValue(newShape.getValue());
		oldShape.setArea(newShape.getArea());
		oldShape.setPerimeter(newShape.getPerimeter());
		oldShape.setURN(buildUrn(id));
		return shapeRepository.save(oldShape);

	}

	private String buildUrn(Long id) {
		return URN_PREFIX + id;
	}
}
