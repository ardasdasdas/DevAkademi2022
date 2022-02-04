package com.sahibinden.devakademi.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(DevAkademiDemoException.class)
	public ResponseEntity<Object> handleException(DevAkademiDemoException ex) {
		log.error(ex.getMessage(), ex);
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(PageNotFoundException.class)
	public ResponseEntity<Object> handleException(PageNotFoundException ex) {
		log.error(ex.getMessage(), ex);
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> handleException(NotFoundException ex) {
		log.error(ex.getMessage(), ex);
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
}
