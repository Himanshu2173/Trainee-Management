package com.example.springbootbaseproject.exceptions;

public class TraineeNotFoundException extends RuntimeException {
	public TraineeNotFoundException(String msg) {
		super(msg);
	}
}
