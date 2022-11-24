package com.teste.miniautorizador.exception;

public class CartaoJaExisteException extends RuntimeException {

	private static final long serialVersionUID = -96004000798200279L;
	
	public CartaoJaExisteException(String message) {
	    super(message);
	  }

}
