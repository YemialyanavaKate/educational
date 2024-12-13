package by.ita.je.error;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Date;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(HttpServerErrorException.class)
    public String handleHttpServerErrorException(HttpServerErrorException ex, Model model) {
        ErrorDetails  errorDetails  =  new  ErrorDetails ( new Date(), ex.getLocalizedMessage(), ex.getStatusCode());
        model.addAttribute("errorDetails", errorDetails);
        return  "error.html";
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public String handleHttpClientErrorException(HttpClientErrorException ex, Model model) {
        ErrorDetails  errorDetails  =  new  ErrorDetails ( new Date(), ex.getLocalizedMessage(), ex.getStatusCode());
        model.addAttribute("errorDetails", errorDetails);
        return  "error.html";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        ErrorDetails  errorDetails  =  new  ErrorDetails ( new Date(), ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        model.addAttribute("errorDetails", errorDetails);
        return  "error.html";
    }
}
