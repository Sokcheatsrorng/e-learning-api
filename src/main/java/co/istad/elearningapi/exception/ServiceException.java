package co.istad.elearningapi.exception;

import co.istad.elearningapi.base.BaseError;
import co.istad.elearningapi.base.BaseErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class ServiceException {
    @ExceptionHandler(ResponseStatusException.class)
    ResponseEntity<?> handleServiceError(ResponseStatusException ex) {

        BaseError<String> basedError = new BaseError<>();
        basedError.setCode(ex.getStatusCode().toString());
        basedError.setDescription(ex.getReason());

        BaseErrorResponse basedErrorResponse = new BaseErrorResponse();
        basedErrorResponse.setError(basedError);

        return ResponseEntity.status(ex.getStatusCode())
                .body(basedErrorResponse);
    }

}
