package com.sahibinden.devakademi.demo.exception;

public class NotFoundException extends DevAkademiDemoException {

	private static final long serialVersionUID = -194738398588197452L;

	public NotFoundException() {
		super();
	}

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public NotFoundException(Throwable throwable) {
		super(throwable);
	}
}
