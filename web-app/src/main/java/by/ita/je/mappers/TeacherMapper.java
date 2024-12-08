package by.ita.je.mappers;


import by.ita.je.dto.TeacherWebDto;
import by.ita.je.models.Teacher;
import org.springframework.stereotype.Component;


@Component
public class TeacherMapper {



    public TeacherWebDto toWebDto(Teacher teacher) {

        return TeacherWebDto.builder()
                .number(teacher.getNumber())
                .name(teacher.getName())
                .surname(teacher.getSurname())
                .build();
    }

    public Teacher toEntityFromWebDto(TeacherWebDto teacherWebDto) {

        return Teacher.builder()
                .number(teacherWebDto.getNumber())
                .name(teacherWebDto.getName())
                .surname(teacherWebDto.getSurname())
                .build();
    }
}
