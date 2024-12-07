package by.ita.je.dto.to_data_base;

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
