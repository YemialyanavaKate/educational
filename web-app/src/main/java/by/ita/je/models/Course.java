package by.ita.je.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Course {
   /* @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "course_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "5"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )*/
    private Integer number;
    private String name;
    private String location;
    private BigDecimal price;
    private Integer duration;
    private ZonedDateTime start;
    private ZonedDateTime stop;
    private List<Student> students;
    private Teacher teacher;
    private Category category;
}
