package by.ita.je.dto.to_web;

import by.ita.je.dto.to_data_base.CategoryDto;
import by.ita.je.dto.to_data_base.CourseDto;
import by.ita.je.mappers.CategoryConverter;
import lombok.*;

import javax.persistence.Convert;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryWebDto {
        private Integer number;
        @Convert(converter = CategoryConverter.class)
        private CategoryDto.CategoryEnum category;

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
