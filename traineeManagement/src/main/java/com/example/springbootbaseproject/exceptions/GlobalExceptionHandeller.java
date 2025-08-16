package com.example.springbootbaseproject.exceptions;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandeller {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map>forValidationException(MethodArgumentNotValidException ex){
		Map<String, String>errorMap=new LinkedHashMap<String, String>();
		
		ex.getAllErrors().forEach(obj->{
			FieldError fError=(FieldError)obj;
			errorMap.put(fError.getField(),fError.getDefaultMessage());
		});
		return new ResponseEntity<Map>(errorMap, HttpStatus.BAD_REQUEST);
	}
		@ExceptionHandler(TraineeNotFoundException.class)
		public ResponseEntity<String>forTraineeNotFoundException(TraineeNotFoundException ex){
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
		@ExceptionHandler(TechnologyNotFoundException.class)
		public ResponseEntity<String>forTechnologyNotFoundException(TechnologyNotFoundException ex){
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		@ExceptionHandler(DuplicateKeyException.class)
		public ResponseEntity<String>forDuplicateKeyException(DuplicateKeyException ex){
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
		}
}
