package by.ita.je.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    private Integer number;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String role;
    private BigDecimal balance;
    private List<CourseDto> courses;

}
