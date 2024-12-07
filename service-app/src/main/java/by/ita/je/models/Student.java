package by.ita.je.models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "student_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "5"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private Integer number;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String role;
    private BigDecimal balance;

    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "students")
    private List<Course> courses;

}
