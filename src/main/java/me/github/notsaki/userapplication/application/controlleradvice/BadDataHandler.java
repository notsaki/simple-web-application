package me.github.notsaki.userapplication.application.controlleradvice;

import me.github.notsaki.userapplication.domain.exception.BadDataException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Handle invalid data format.
 */
@ControllerAdvice
public class BadDataHandler {

	@ExceptionHandler(BadDataException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	protected void handleValidation() {
	}
}
