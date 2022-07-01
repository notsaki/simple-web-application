package me.github.notsaki.userapplication.application.controlleradvice;

import me.github.notsaki.userapplication.domain.data.error.ValidationInfo;
import me.github.notsaki.userapplication.domain.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * Handler for request body validation error.
 */
@ControllerAdvice
public class ValidationHandler {

	/**
	 * @return A list of objects with the target properties that is invalid and instructions on how to fix them.
	 */
	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	@ResponseBody
	protected List<ValidationInfo> handleValidation(ValidationException exception) {
		return exception.getValidationInfo();
	}
}
