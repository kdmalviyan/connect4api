package com.kuldeepsingh.connect4.exceptions;

public class InvalidUserMoveException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidUserMoveException(String message) {
		super(message);
	}

}
