package com.spotserver.model;

public class Status {

	private boolean isOk;
	private String message;

	public Status() {
		super();
	}

	public Status(boolean isOk, String message) {
		super();
		this.isOk = isOk;
		this.message = message;
	}

	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
