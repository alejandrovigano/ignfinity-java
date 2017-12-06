package ar.com.vga.ignfinity.core.exception;

public class IgnfinityException extends Exception{
	private static final long serialVersionUID = 1L;

	public IgnfinityException() {
		super();
	}

	public IgnfinityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public IgnfinityException(String message, Throwable cause) {
		super(message, cause);
	}

	public IgnfinityException(String message) {
		super(message);
	}

	public IgnfinityException(Throwable cause) {
		super(cause);
	}

	
}
