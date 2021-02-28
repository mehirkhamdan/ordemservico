package edu.br.ifpr.os.exception;

public class EmailJaExisteException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public EmailJaExisteException(String email) {
		super(email);
	}

}
