package me.surreallobster.restfulShapes.domain;

import java.util.Objects;

public class Square extends BaseShape {
	
	private Integer side;

	public Square() {
	}

	public Square(Long id, Integer side, Double area, Double perimeter, String urn) {
		this.setType("square");
		this.setId(id);
		this.setURN(urn);
		this.side = side;
		this.setArea(area);
		this.setPerimeter(perimeter);
	}

	public Integer getSide() {
		return side;
	}

	public void setSide(Integer side) {
		this.side = side;
	}

	public static Double calculateArea(Integer side) {
		return Math.pow((side), 2);

	}

	public static Double calculatePerimeter(Integer side) {
		return Double.valueOf(4 * side);

	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Square)) {
			return false;
		}
		Square square = (Square) o;
		return Objects.equals(this.side, square.side);
	}

}
