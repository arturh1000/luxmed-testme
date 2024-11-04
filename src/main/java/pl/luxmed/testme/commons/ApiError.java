package pl.luxmed.testme.commons;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class ApiError implements LuxmedTestAppResponseBody {

    private HttpStatus status;
    private String message;
    @Singular("error")
    private List<String> errors;

    public ApiError(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Collections.singletonList(error);
    }
}