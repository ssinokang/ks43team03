package ks43team03.exception;

public class NotFoundOrderException extends IllegalArgumentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundOrderException(String message) {
		super(message);
	}

	public NotFoundOrderException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundOrderException(Throwable cause) {
		super(cause);
	}

}
