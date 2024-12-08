package by.ita.je.models;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {
    @Id
    private Integer number;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String role;
    private BigDecimal balance;

    //@ToString.Exclude
    private List<Course> courses;

}
