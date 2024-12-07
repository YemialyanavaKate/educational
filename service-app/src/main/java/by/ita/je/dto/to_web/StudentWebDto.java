package by.ita.je.dto.to_web;

import by.ita.je.dto.to_data_base.CourseDto;
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
    private BigDecimal balance;
    private List<CourseDto> courses;

}
