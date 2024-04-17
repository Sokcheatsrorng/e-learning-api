package co.istad.elearningapi.exception;

import co.istad.elearningapi.base.BaseError;
import co.istad.elearningapi.base.BaseErrorResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ValidationException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    BaseErrorResponse handleValidationErrors(MethodArgumentNotValidException ex) {

        BaseError<List<?>> baseError = new BaseError<>();
        List<Map<String, Object>> errors = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            Map<String, Object> error = new HashMap<>();
            error.put("field", fieldError.getField());
            error.put("reason", fieldError.getDefaultMessage());
            errors.add(error);
        });

        baseError.setCode(HttpStatus.CONFLICT.getReasonPhrase());
        baseError.setDescription(errors);

        // Customizing the response format
        return new BaseErrorResponse(baseError);
    }

}


