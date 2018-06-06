package com.stackroute.moviecruiser.exception;


@SuppressWarnings("serial")
public class MovieNotFoundException extends Exception {

	String message;
	public String getmessage() {
		return message;
	}

	public void setmessage(String message) {
		this.message = message;
	}

	public MovieNotFoundException(final String message) {
		super(message);
		this.message= message;
	}

	@Override
	public String toString() {
		return "MovieNotFoundException [message=" + message + "]";
	}

	
}
