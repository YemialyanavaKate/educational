package by.ita.je.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Teacher {
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "teacher_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "3"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private Integer number;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher")
    private List<Course> courses;
}
