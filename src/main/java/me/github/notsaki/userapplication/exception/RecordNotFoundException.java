package me.github.notsaki.userapplication.exception;

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
