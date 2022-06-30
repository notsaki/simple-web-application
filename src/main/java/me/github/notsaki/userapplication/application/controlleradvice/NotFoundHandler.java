package me.github.notsaki.userapplication.application.controlleradvice;

import me.github.notsaki.userapplication.domain.data.error.GenericError;
import me.github.notsaki.userapplication.infrastructure.data.error.GenericErrorEntity;
import me.github.notsaki.userapplication.infrastructure.exception.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Handles not found exceptions.
 */
@ControllerAdvice
public class NotFoundHandler {
	/**
	 * @param exception the thrown exception.
	 * @return An object containing what information wasn't found in the context (like a user ID), and a message
	 * describing the error.
	 */
	@ExceptionHandler(RecordNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public GenericError<Object> handleRecordNotFound(RecordNotFoundException exception) {
		var context = exception.getContext();
		return new GenericErrorEntity<>(context);
	}
}
