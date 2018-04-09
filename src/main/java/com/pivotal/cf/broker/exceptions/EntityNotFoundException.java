package com.pivotal.cf.broker.exceptions;

public class EntityNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5675348789776539732L;

	public EntityNotFoundException() {
		super();
	}

	public EntityNotFoundException(String message) {
		super(message);
	}

	public EntityNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
