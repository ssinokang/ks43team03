package ks43team03.error;

public class NotFoundGoodsException extends IllegalArgumentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NotFoundGoodsException(String message) {
		super(message);
	}

	public NotFoundGoodsException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundGoodsException(Throwable cause) {
		super(cause);
	}

}
