package by.ita.je.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentWebDto {
    private Integer number;
    private String name;
    private String surname;
    private String role;
    private String login;
    private String password;
    private BigDecimal balance;
    private List<CourseWebDto> courses;

}
