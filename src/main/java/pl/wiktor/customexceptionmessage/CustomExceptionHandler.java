package pl.wiktor.customexceptionmessage;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(CustomException.class)
    public @ResponseBody
    ResponseEntity<?> getError() {
        return new ResponseEntity<>(new ResponseMessage("Custom Exception!"), HttpStatus.BAD_REQUEST);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ArrayList<ValidationCustomResponse> errors = new ArrayList<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(ValidationCustomResponse.builder().field(error.getField()).message(error.getDefaultMessage()).build());
        }

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getMethod());
        builder.append(" method is not supported for this request. Supported methods are: ");
        ex.getSupportedHttpMethods().forEach(t -> builder.append(t).append(" "));

        return new ResponseEntity<>(new ResponseMessage(builder.toString()), HttpStatus.METHOD_NOT_ALLOWED);
    }
}
