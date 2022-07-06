package me.surreallobster.restfulShapes.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ShapeNotFoundAdvice {
	
	@ResponseBody
	@ExceptionHandler(ShapeNotFoundException.class)
	ResponseEntity<ShapeNotFoundErrorResponse> shapeNotFoundHandler(ShapeNotFoundException ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ShapeNotFoundErrorResponse(ex.getMessage()));		
	}

}
