package by.ita.je.mappers;

import by.ita.je.dto.to_data_base.StudentDto;
import by.ita.je.dto.to_web.StudentWebDto;
import by.ita.je.models.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public StudentDto toDataBaseDto(Student student) {

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

    public Student toEntityFromDataBase(StudentDto studentDto) {

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

    public StudentWebDto toWebDto(Student student) {

        return StudentWebDto.builder()
                .number(student.getNumber())
                .name(student.getName())
                .surname(student.getSurname())
                .login(student.getLogin())
                .password(student.getPassword())
                .role(student.getRole())
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
