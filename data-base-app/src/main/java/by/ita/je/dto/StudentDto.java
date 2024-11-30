package by.ita.je.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    private Integer number;
    private String name;
    private String surname;
    @JsonIgnore
    private List<CourseDto> courses;

}
