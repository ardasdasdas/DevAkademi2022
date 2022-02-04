package com.sahibinden.devakademi.demo.exception;

public class PageNotFoundException extends DevAkademiDemoException {

	private static final long serialVersionUID = 3456193950350117332L;

	public PageNotFoundException() {
		super();
	}

	public PageNotFoundException(String message) {
		super(message);
	}

	public PageNotFoundException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public PageNotFoundException(Throwable throwable) {
		super(throwable);
	}
}
