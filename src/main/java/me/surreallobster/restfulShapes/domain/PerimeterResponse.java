package me.surreallobster.restfulShapes.domain;

public class PerimeterResponse {
	
	private Long id;
	private String URN;
	private Double perimeter;

	public Double getPerimeter() {
		return perimeter;
	}

	public void setPerimeter(Double perimeter) {
		this.perimeter = perimeter;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getURN() {
		return URN;
	}

	public void setURN(String uRN) {
		URN = uRN;
	}

}
