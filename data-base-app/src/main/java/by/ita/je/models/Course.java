package by.ita.je.models;

import by.ita.je.models.Category;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer number;
    private String name;
    private String location;
    private BigDecimal price;
    private Integer duration;
    private ZonedDateTime start;
    private ZonedDateTime stop;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "course_number"),
            inverseJoinColumns = @JoinColumn(name = "student_number")
    )
    private List<Student> students;

    @ToString.Exclude
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Teacher teacher;

    @OneToOne
    @JoinColumn(name = "category_number")
    private Category category;
}
