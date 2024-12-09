package by.ita.je.mappers;

import by.ita.je.dto.to_data_base.TeacherDto;
import by.ita.je.dto.to_web.TeacherWebDto;
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

    public TeacherWebDto toWebDto(Teacher teacher) {

        return TeacherWebDto.builder()
                .number(teacher.getNumber())
                .name(teacher.getName())
                .surname(teacher.getSurname())
                .login(teacher.getLogin())
                .password(teacher.getPassword())
                .role(teacher.getRole())
                .build();
    }

    public Teacher toEntityFromWebDto(TeacherWebDto teacherWebDto) {

        return Teacher.builder()
                .number(teacherWebDto.getNumber())
                .name(teacherWebDto.getName())
                .surname(teacherWebDto.getSurname())
                .login(teacherWebDto.getLogin())
                .password(teacherWebDto.getPassword())
                .role(teacherWebDto.getRole())
                .build();
    }
}
