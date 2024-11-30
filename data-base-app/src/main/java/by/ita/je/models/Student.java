package by.ita.je.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer number;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String role;

    //@ToString.Exclude
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "students")
    private List<Course> courses;

}
