package by.ita.je.mappers;

import by.ita.je.dto.TeacherDto;
import by.ita.je.models.Teacher;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TeacherMapper {

    public TeacherDto toDto(Teacher teacher){

        return TeacherDto.builder()
                .number(teacher.getNumber())
                .name(teacher.getName())
                .surname(teacher.getSurname())
                .build();
    }

    public Teacher toEntity (TeacherDto teacherDto){
        return Teacher.builder()
                .number(teacherDto.getNumber())
                .name(teacherDto.getName())
                .build();
    }
}
