package me.surreallobster.restfulShapes.controllers;

public class ShapeNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	ShapeNotFoundException(Long id){
		super("Could not find shape with id: " + id);
	}

}
