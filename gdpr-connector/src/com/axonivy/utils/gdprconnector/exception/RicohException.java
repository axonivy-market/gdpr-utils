package com.axonivy.utils.gdprconnector.exception;

public class RicohException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public RicohException() {
		super();
	}

	public RicohException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RicohException(String message, Throwable cause) {
		super(message, cause);
	}

	public RicohException(String message) {
		super(message);
	}

	public RicohException(Throwable cause) {
		super(cause);
	}
}
