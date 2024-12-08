package by.ita.je.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherWebDto {
    private Integer number;
    private String name;
    private String surname;
    private String role;
    private List<CourseWebDto> courses;
}
