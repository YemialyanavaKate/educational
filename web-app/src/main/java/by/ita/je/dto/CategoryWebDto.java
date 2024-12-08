package by.ita.je.dto;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryWebDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer number;

    private CategoryEnum categoryEnum;

    @Getter
    public enum CategoryEnum {
        IT,
        MANAGEMENT
    }

    //private List<Course> courses;

}
