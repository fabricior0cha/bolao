package br.com.anhembi.bolao.exception;

public class UniqueException extends Exception {

	private static final long serialVersionUID = 1L;

	public UniqueException(String message) {
		super(message);
	}
}
