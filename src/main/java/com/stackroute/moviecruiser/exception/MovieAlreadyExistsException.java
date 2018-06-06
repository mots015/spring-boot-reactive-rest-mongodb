package com.stackroute.moviecruiser.exception;




public class MovieAlreadyExistsException extends Exception {

	/**
	 * constructor
	 * 
	 * @param message
	 */
	
	String message;
	public String getmessage() {
		return message;
	}

	public void setmessage(String message) {
		this.message = message;
	}

	public MovieAlreadyExistsException(final String message) {
		super(message);
		this.message= message;
	}

	@Override
	public String toString() {
		return "MovieAlreadyExistsException [message=" + message + "]";
	}

	

	


}
