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
public class RegistrationWebDto {
    private Integer number;
    private String name;
    private String surname;
}
