package by.ita.je.dto.to_data_base;

import lombok.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {
    private Integer number;
    private String name;
    private String location;
    private BigDecimal price;
    private Integer duration;
    private ZonedDateTime start;
    private ZonedDateTime stop;
    //@Singular
    private List<StudentDto> students;
    private TeacherDto teacher;
    private CategoryDto category;
}
