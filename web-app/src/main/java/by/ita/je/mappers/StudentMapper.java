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
                .balance(student.getBalance())
                .build();
    }

    public Student toEntityFromWebDto(StudentWebDto studentWebDto) {

        return Student.builder()
                .number(studentWebDto.getNumber())
                .name(studentWebDto.getName())
                .surname(studentWebDto.getSurname())
                .balance(studentWebDto.getBalance())
                .build();
    }

}
