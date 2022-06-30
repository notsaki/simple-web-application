package me.github.notsaki.userapplication.infrastructure.data.error;

import me.github.notsaki.userapplication.domain.data.error.GenericError;

public class GenericErrorEntity<T> implements GenericError<T> {
	private final T context;
	private final String message;

	public GenericErrorEntity(T context, String message) {
		this.context = context;
		this.message = message;
	}

	public GenericErrorEntity(T context) {
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
