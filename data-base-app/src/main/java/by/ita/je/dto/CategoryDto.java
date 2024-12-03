package by.ita.je.dto;

import by.ita.je.mappers.CategoryConverter;
import lombok.*;

import javax.persistence.Convert;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private Integer number;
    @Convert(converter = CategoryConverter.class)
    private CategoryEnum category;

    @Getter
    public enum CategoryEnum {
        IT(1),
        MANAGEMENT(2);

        private Integer code;

        CategoryEnum(Integer code) {
            this.code = code;
        }
    }

    private CourseDto course;
}
