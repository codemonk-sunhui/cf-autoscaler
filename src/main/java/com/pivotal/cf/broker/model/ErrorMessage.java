package com.pivotal.cf.broker.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorMessage {

	@JsonProperty("message")
	private String message;

	public ErrorMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
