package by.ita.je.error;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@Setter
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private HttpStatus statusCode;

    public ErrorDetails(Date timestamp, String message, HttpStatus statusCode) {
        this.timestamp = timestamp;
        this.message = message;
        this.statusCode = statusCode;
           }
}
