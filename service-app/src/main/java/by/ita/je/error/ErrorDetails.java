package by.ita.je.error;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ErrorDetails {
    private Date timestamp;
    private String message;

    public  ErrorDetails (Date timestamp, String message) {
        this .timestamp = timestamp;
        this .message = message;
           }
}
