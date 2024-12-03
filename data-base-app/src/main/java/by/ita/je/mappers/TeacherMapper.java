package by.ita.je.mappers;

import by.ita.je.dto.TeacherDto;
import by.ita.je.models.Teacher;
import org.springframework.stereotype.Component;


@Component
public class TeacherMapper {

    public TeacherDto toDto(Teacher teacher) {

        return TeacherDto.builder()
                .number(teacher.getNumber())
                .name(teacher.getName())
                .surname(teacher.getSurname())
                .login(teacher.getLogin())
                .password(teacher.getPassword())
                .role(teacher.getRole())
                .build();
    }

    public Teacher toEntity(TeacherDto teacherDto) {

        return Teacher.builder()
                .number(teacherDto.getNumber())
                .name(teacherDto.getName())
                .surname(teacherDto.getSurname())
                .login(teacherDto.getLogin())
                .password(teacherDto.getPassword())
                .role(teacherDto.getRole())
                .build();
    }
}
