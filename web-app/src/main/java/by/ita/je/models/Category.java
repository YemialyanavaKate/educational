package by.ita.je.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category {
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
