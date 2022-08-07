package ru.nvkz.socks.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.nvkz.socks.dto.ErrorDto;
import ru.nvkz.socks.exception.NotEnoughException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandlerConfig {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleMissingServletRequestParameterException(MissingServletRequestParameterException exception) {
        log.error("", exception);
        return new ErrorDto(exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handleException(Exception exception) {
        log.error("", exception);
        return new ErrorDto(exception.getMessage());
    }

    @ExceptionHandler(NotEnoughException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ErrorDto handleNotEnoughException(NotEnoughException exception) {
        log.error("", exception);
        return new ErrorDto(exception.getMessage());
    }

}
