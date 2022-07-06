package me.surreallobster.restfulShapes.domain;

import java.util.ArrayList;
import java.util.List;

public class ShapeRequest {
	
	private String type;
	private List<Integer> values;

	public ShapeRequest() {
		values = new ArrayList<Integer>();
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Integer> getValues() {
		return values;
	}

	public void setValues(List<Integer> values) {
		this.values = values;
	}
	
}
