package me.surreallobster.restfulShapes.controllers;

public class ShapeNotFoundErrorResponse {

	private String errorMessage;

	public ShapeNotFoundErrorResponse(String message) {
		errorMessage = message;
	}
	

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
