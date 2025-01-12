package by.ita.je.dto.to_web;

import by.ita.je.models.Category;
import lombok.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseWebDto {
    private Integer number;
    private String name;
    private String location;
    private BigDecimal price;
    private Integer duration;
    private ZonedDateTime start;
    private ZonedDateTime stop;
    @Singular
    private List<StudentWebDto> students;
    private TeacherWebDto teacher;
    private Category category;
}
