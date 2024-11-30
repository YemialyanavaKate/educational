package by.ita.je.mappers;

import by.ita.je.dto.StudentDto;
import by.ita.je.models.Student;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class StudentMapper {

    public StudentDto toDto(Student student){

        return StudentDto.builder()
                .number(student.getNumber())
                .name(student.getName())
                .surname(student.getSurname())
                .build();
    }

    public Student toEntity (StudentDto studentDto){
        return Student.builder()
                .number(studentDto.getNumber())
                .name(studentDto.getName())
                .surname(studentDto.getSurname())
                .build();
    }
}
