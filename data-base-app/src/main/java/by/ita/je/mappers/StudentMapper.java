package by.ita.je.mappers;

import by.ita.je.dto.StudentDto;
import by.ita.je.models.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public StudentDto toDto(Student student) {

        return StudentDto.builder()
                .number(student.getNumber())
                .name(student.getName())
                .surname(student.getSurname())
                .login(student.getLogin())
                .password(student.getPassword())
                .role(student.getRole())
                .balance(student.getBalance())
                .build();
    }

    public Student toEntity(StudentDto studentDto) {

        return Student.builder()
                .number(studentDto.getNumber())
                .name(studentDto.getName())
                .surname(studentDto.getSurname())
                .login(studentDto.getLogin())
                .password(studentDto.getPassword())
                .role(studentDto.getRole())
                .balance(studentDto.getBalance())
                .build();
    }
}
