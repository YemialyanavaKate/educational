package by.ita.je.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Date;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<Object> handleHttpServerErrorException(HttpServerErrorException ex) {
        ErrorDetails  errorDetails  =  new  ErrorDetails ( new Date(), ex.getMessage());
        return  new  ResponseEntity <>(errorDetails, ex.getStatusCode());
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException ex) {
        ErrorDetails  errorDetails  =  new  ErrorDetails ( new Date(), ex.getMessage());
        return  new  ResponseEntity <>(errorDetails, ex.getStatusCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        ErrorDetails  errorDetails  =  new  ErrorDetails ( new Date(), ex.getMessage());
        return  new  ResponseEntity <>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
