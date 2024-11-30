package by.ita.je.models;

import by.ita.je.mappers.CategoryConverter;
import jakarta.persistence.*;
import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @OneToOne(cascade = CascadeType.ALL)
    private Course course;
}
