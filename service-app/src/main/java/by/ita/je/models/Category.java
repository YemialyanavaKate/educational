package by.ita.je.models;

import by.ita.je.mappers.CategoryConverter;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer number;

    @Convert(converter = CategoryConverter.class)
    private CategoryEnum category;

    @Getter
    public enum CategoryEnum {
        IT("IT"),
        MANAGEMENT("MANAGEMENT");

        private String code;

        CategoryEnum(String code) {
            this.code = code;
        }
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private List<Course> courses;
}
