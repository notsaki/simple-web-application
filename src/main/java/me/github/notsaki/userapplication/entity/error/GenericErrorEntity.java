package me.github.notsaki.userapplication.entity.error;

import me.github.notsaki.userapplication.domain.entity.error.GenericError;

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
