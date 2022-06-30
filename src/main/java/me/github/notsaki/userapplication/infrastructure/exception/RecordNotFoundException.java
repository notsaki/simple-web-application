package me.github.notsaki.userapplication.infrastructure.exception;

/**
 * Exception that is thrown when any kind of entity is missing. The class is usually used in find, delete and update
 * operations.
 */
public class RecordNotFoundException extends Exception {
	private final Object context;

	public RecordNotFoundException(Object context, String message) {
		super(message);
		this.context = context;
	}

	public RecordNotFoundException(Object context) {
		super();
		this.context = context;
	}

	public Object getContext() {
		return context;
	}
}
