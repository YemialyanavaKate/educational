package by.ita.je.mappers;

import by.ita.je.dto.CourseWebDto;
import by.ita.je.dto.StudentWebDto;

import by.ita.je.dto.TeacherWebDto;
import by.ita.je.models.Course;
import by.ita.je.models.Student;
import by.ita.je.models.Teacher;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CourseMapper {
    private StudentMapper studentMapper;

    private TeacherMapper teacherMapper;
    private CategoryMapper categoryMapper;

    public CourseWebDto toWebDTO(Course course) {

        List<StudentWebDto> studentWebDtos = course.getStudents() != null ?
                course.getStudents()
                        .stream()
                        .map(studentMapper::toWebDto)
                        .collect(Collectors.toList()) :
                Collections.emptyList();
        TeacherWebDto teacherWebDto = course.getTeacher() != null ?
                teacherMapper.toWebDto(course.getTeacher())
                : null;


        return CourseWebDto.builder()
                .number(course.getNumber())
                .name(course.getName())
                .location(course.getLocation())
                .price(course.getPrice())
                .duration(course.getDuration())
                .start(course.getStart())
                .stop(course.getStop())
                .students(studentWebDtos)
                .teacher(teacherWebDto)
                .categoryWebDto(categoryMapper.toWebDtoFromEntity(course.getCategory()))
                .build();
    }

    public Course toEntityFromWebDto(CourseWebDto courseWebDto) {

        List<Student> students = courseWebDto.getStudents() != null ?
                courseWebDto.getStudents().stream()
                        .map(studentMapper::toEntityFromWebDto)
                        .toList()
                : Collections.emptyList();
        Teacher teacher = courseWebDto.getTeacher() != null ?
                teacherMapper.toEntityFromWebDto(courseWebDto.getTeacher()) : null;
        return Course.builder()
                .number(courseWebDto.getNumber())
                .name(courseWebDto.getName())
                .location(courseWebDto.getLocation())
                .price(courseWebDto.getPrice())
                .duration(courseWebDto.getDuration())
                .start(courseWebDto.getStart())
                .stop(courseWebDto.getStop())
                .students(students)
                .teacher(teacher)
                .category(categoryMapper.toEntityFromWebDto(courseWebDto.getCategoryWebDto()))
                .build();
    }
}
