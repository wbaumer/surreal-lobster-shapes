package me.surreallobster.restfulShapes.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ShapeResponse {

	@JsonInclude(Include.NON_EMPTY)
	private List<Circle> circles;
	@JsonInclude(Include.NON_EMPTY)
	private List<Square> squares;

	public ShapeResponse() {
		circles = new ArrayList<Circle>();
		squares = new ArrayList<Square>();
	}

	public ShapeResponse(List<Circle> circles) {
		this.circles = circles;
	}

	public List<Circle> getCircles() {
		return circles;
	}

	public List<Square> getSquares() {
		return squares;
	}

	public void addCircle(Circle circle) {
		circles.add(circle);
	}

	public void addSquare(Square square) {
		squares.add(square);
	}

}
