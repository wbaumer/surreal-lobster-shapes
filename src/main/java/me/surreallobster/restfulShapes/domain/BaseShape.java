package me.surreallobster.restfulShapes.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
public class BaseShape {

	private @Id @GeneratedValue Long id;
	@JsonInclude(Include.NON_NULL)
	private String type;
	@JsonInclude(Include.NON_NULL)
	Integer value;
	@JsonInclude(Include.NON_NULL)
	private Double area;
	@JsonInclude(Include.NON_NULL)
	private Double perimeter;
	@JsonInclude(Include.NON_NULL)
	private String URN;

	public BaseShape() {
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public Double getPerimeter() {
		return perimeter;
	}

	public void setPerimeter(Double perimeter) {
		this.perimeter = perimeter;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
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

	public void setURN(String URN) {
		this.URN = URN;
	}

}
