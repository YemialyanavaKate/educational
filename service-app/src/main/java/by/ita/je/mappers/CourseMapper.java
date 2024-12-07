package by.ita.je.mappers;

import by.ita.je.dto.to_data_base.CategoryDto;
import by.ita.je.dto.to_data_base.CourseDto;
import by.ita.je.dto.to_data_base.StudentDto;
import by.ita.je.dto.to_data_base.TeacherDto;
import by.ita.je.dto.to_web.CategoryWebDto;
import by.ita.je.dto.to_web.CourseWebDto;
import by.ita.je.dto.to_web.StudentWebDto;
import by.ita.je.dto.to_web.TeacherWebDto;
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
                        .map(studentMapper::toDataBaseDto)
                        .collect(Collectors.toList()) :
                Collections.emptyList();
        TeacherDto teacherDto = course.getTeacher() != null ?
                teacherMapper.toDto(course.getTeacher()) : null;
        CategoryDto categoryDto = course.getCategory() != null ?
                categoryMapper.toDto(course.getCategory()) : null;

        return CourseDto.builder()
                .number(course.getNumber())
                .name(course.getName())
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
                        .map(studentMapper::toEntityFromDataBase)
                        .toList()
                : Collections.emptyList();
        Teacher teacher = courseDto.getTeacher() != null ?
                teacherMapper.toEntity(courseDto.getTeacher()) : null;
        Category category = courseDto.getCategory() != null ?
                categoryMapper.toEntity(courseDto.getCategory()) : null;
        return Course.builder()
                .number(courseDto.getNumber())
                .name(courseDto.getName())
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

    public CourseWebDto toWebDTO(Course course) {

        List<StudentWebDto> studentWebDtos = course.getStudents() != null ?
                course.getStudents()
                        .stream()
                        .map(studentMapper::toWebDto)
                        .collect(Collectors.toList()) :
                Collections.emptyList();
        TeacherWebDto teacherWebDto = course.getTeacher() != null ?
                teacherMapper.toWebDto(course.getTeacher()) : null;

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
                .category(course.getCategory())
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
                .category(courseWebDto.getCategory())
                .build();
    }
}
