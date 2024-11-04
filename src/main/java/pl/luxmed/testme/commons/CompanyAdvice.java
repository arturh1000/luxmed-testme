package pl.luxmed.testme.commons;

import jakarta.validation.UnexpectedTypeException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.lang.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.luxmed.testme.api.CompanyController;

@ControllerAdvice(assignableTypes = CompanyController.class)
public class CompanyAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, @NonNull HttpHeaders headers, @NonNull HttpStatusCode status,
            @NonNull WebRequest request
    ) {
        var errors = ex.getBindingResult()
                .getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        return ResponseEntity.status(status).body(errors);
    }

    @ExceptionHandler({ UnexpectedTypeException.class })
    public ResponseEntity<Object> handleUnexpectedType(UnexpectedTypeException ex) {
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(ex.getLocalizedMessage())
                .error("Unexpected type occurred").build();

        logger.warn("Unexpected type occurred. {}", ex);
        return ResponseEntity.badRequest().body(apiError);
    }

    //TODO: obsłużyć utrate połączenie z db w trakcie pracy aplikacji
    //https://stackoverflow.com/questions/32052092/constraintviolationexception-in-spring-rest

    @ExceptionHandler({ DataIntegrityViolationException.class })
    public ResponseEntity<Object> handleConstraintViolationException(DataIntegrityViolationException ex, WebRequest request) {
        logger.warn("DataIntegrityViolationException exception occurred. {}", ex);
        ApiError apiError = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(),
                "Error occurred"
        );
        return ResponseEntity.internalServerError().body(apiError);
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAllUnhaldledExceptions(Exception ex, WebRequest request) {
        logger.warn("Unexpected exception occurred. {}", ex);
        ApiError apiError = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error",
                "Error occurred"
        );
        return ResponseEntity.internalServerError().body(apiError);
    }
}