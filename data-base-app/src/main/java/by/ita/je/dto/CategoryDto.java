package by.ita.je.dto;

import by.ita.je.mappers.CategoryConverter;
import jakarta.persistence.*;
import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private Integer id;
    @Convert(converter = CategoryConverter.class)
    private CategoryEnum category;

    @Getter
    public enum CategoryEnum {
        AI(1),
        JAVA(2),
        IT_HR(3),
        PYTHON(4);

        private Integer code;

        CategoryEnum(Integer code) {
            this.code = code;
        }
    }

    private CourseDto courseDto;
}
