package by.ita.je.mappers;

import by.ita.je.dto.CategoryDto;
import by.ita.je.dto.CourseDto;
import by.ita.je.dto.StudentDto;
import by.ita.je.dto.TeacherDto;
import by.ita.je.models.Category;
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

    public CourseDto toDTO(Course course) {

        List<StudentDto> studentDtos = course.getStudents() != null ?
                course.getStudents()
                        .stream()
                        .map(studentMapper::toDto)
                        .collect(Collectors.toList()) :
                Collections.emptyList();

        TeacherDto teacherDto = course.getTeacher() != null ?
                teacherMapper.toDto(course.getTeacher()) : null;
        CategoryDto categoryDto = course.getCategory() != null ?
                categoryMapper.toDto(course.getCategory()) : null;

        return CourseDto.builder()
                .number(course.getNumber())
                .location(course.getLocation())
                .price(course.getPrice())
                .duration(course.getDuration())
                .start(course.getStart())
                .stop(course.getStop())
                .students(studentDtos)
                .teacher(teacherDto)
                .category(categoryDto)
                .build();
    }

    public Course toEntity(CourseDto courseDto) {

        List<Student> students = courseDto.getStudents() != null ?
                courseDto.getStudents().stream()
                        .map(studentMapper::toEntity)
                        .collect(Collectors.toList())
                : Collections.emptyList();
        Teacher teacher = courseDto.getTeacher() != null ?
                teacherMapper.toEntity(courseDto.getTeacher()) : null;
        Category category = courseDto.getCategory() != null ?
                categoryMapper.toEntity(courseDto.getCategory()) : null;
        return Course.builder()
                .number(courseDto.getNumber())
                .location(courseDto.getLocation())
                .price(courseDto.getPrice())
                .duration(courseDto.getDuration())
                .start(courseDto.getStart())
                .stop(courseDto.getStop())
                .students(students)
                .teacher(teacher)
                .category(category)
                .build();
    }
}
