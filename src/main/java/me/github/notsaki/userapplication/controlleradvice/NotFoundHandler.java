package me.github.notsaki.userapplication.controlleradvice;

import me.github.notsaki.userapplication.domain.entity.error.GenericError;
import me.github.notsaki.userapplication.exception.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NotFoundHandler {
	@ExceptionHandler(RecordNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public GenericError<Object> handleRecordNotFound(RecordNotFoundException exception) {
		var context = exception.getContext();
		return new GenericError<>(context);
	}
}
