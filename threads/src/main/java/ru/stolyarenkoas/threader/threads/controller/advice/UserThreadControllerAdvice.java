package ru.stolyarenkoas.threader.threads.controller.advice;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.stolyarenkoas.threader.threads.controller.exception.UserHasNoUserThreadsException;
import ru.stolyarenkoas.threader.threads.controller.exception.UserThreadNotFoundException;

import javax.annotation.Nonnull;

/**
 * Extends user thread controller with exception handlers.
 *
 * @author Alexander Stolyarenko (27.06.2021)
 */
@ControllerAdvice
public class UserThreadControllerAdvice {

    /**
     * Handles problems with finding user threads by identifiers.
     *
     * @param exception corresponding exception.
     * @return exception message.
     */
    @Hidden
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({UserThreadNotFoundException.class, UserHasNoUserThreadsException.class})
    public String handleUserThreadNotFound(@Nonnull final RuntimeException exception) {
        return exception.getMessage();
    }

}
