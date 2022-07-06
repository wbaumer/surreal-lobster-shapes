package me.surreallobster.restfulShapes.domain;

import java.util.Objects;


import com.fasterxml.jackson.annotation.JsonIgnore;

public class Circle extends BaseShape {

	private Integer radius;

	public Circle() {
	}

	public Circle(Long id, Integer radius, Double area, Double perimeter, String urn) {
		this.setType("circle");
		this.setId(id);
		this.setURN(urn);
		this.radius = radius;
		this.setArea(area);
		this.setPerimeter(perimeter);
	}

	public Integer getRadius() {
		return radius;
	}

	public void setRadius(Integer radius) {
		this.radius = radius;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Circle)) {
			return false;
		}
		Circle circle = (Circle) o;
		return Objects.equals(this.radius, circle.radius);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.radius, this.getArea(), this.getPerimeter());
	}
	
	@Override
	@JsonIgnore
	public Integer getValue() {
		return radius;
	}
	
	@Override
	public void setValue(Integer value) {
		this.radius = value;
	}

	public static Double calculateArea(Integer radius) {
		return Math.pow((Math.PI * radius), 2);

	}

	public static Double calculatePerimeter(Integer radius) {
		return 2 * Math.PI * radius;

	}

}
