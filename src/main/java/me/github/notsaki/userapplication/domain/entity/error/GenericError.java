package me.github.notsaki.userapplication.domain.entity.error;

public class GenericError<T> {
	private final T context;
	private final String message;

	public GenericError(T context, String message) {
		this.context = context;
		this.message = message;
	}

	public GenericError(T context) {
		this.context = context;
		this.message = "Entity not found.";
	}

	public T getContext() {
		return context;
	}

	public String getMessage() {
		return message;
	}
}
