package com.sahibinden.devakademi.demo.exception;

public class DevAkademiDemoException extends RuntimeException {

	private static final long serialVersionUID = 4323936041413663102L;

	public DevAkademiDemoException() {
		super();
	}

	public DevAkademiDemoException(String message) {
		super(message);
	}

	public DevAkademiDemoException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public DevAkademiDemoException(Throwable throwable) {
		super(throwable);
	}
}
