package by.ita.je.mappers;


import by.ita.je.dto.StudentWebDto;
import by.ita.je.models.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public StudentWebDto toWebDto(Student student) {

        return StudentWebDto.builder()
                .number(student.getNumber())
                .name(student.getName())
                .surname(student.getSurname())
                .role(student.getRole())
                .login(student.getLogin())
                .password(student.getPassword())
                .balance(student.getBalance())
                .build();
    }

    public Student toEntityFromWebDto(StudentWebDto studentWebDto) {

        return Student.builder()
                .number(studentWebDto.getNumber())
                .name(studentWebDto.getName())
                .surname(studentWebDto.getSurname())
                .login(studentWebDto.getLogin())
                .password(studentWebDto.getPassword())
                .role(studentWebDto.getRole())
                .balance(studentWebDto.getBalance())
                .build();
    }
}
