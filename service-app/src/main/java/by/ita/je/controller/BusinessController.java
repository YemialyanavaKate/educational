package by.ita.je.controller;

import by.ita.je.dto.to_data_base.CourseDto;
import by.ita.je.dto.to_web.CourseWebDto;
import by.ita.je.dto.to_web.RegistrationWebDto;
import by.ita.je.dto.to_web.StudentWebDto;
import by.ita.je.dto.to_web.TeacherWebDto;
import by.ita.je.mappers.CourseMapper;
import by.ita.je.mappers.RegistrationMapper;
import by.ita.je.mappers.StudentMapper;
import by.ita.je.mappers.TeacherMapper;
import by.ita.je.models.*;
import by.ita.je.service.BusinessCourseService;
import by.ita.je.service.BusinessStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/business/course")
@RequiredArgsConstructor
public class BusinessController {
    private final BusinessCourseService businessCourseService;
    private final BusinessStudentService businessStudentService;
    private final CourseMapper courseMapper;
    private final TeacherMapper teacherMapper;
    private final RegistrationMapper registrationMapper;
    private final StudentMapper studentMapper;



    @PostMapping("/update")
    public CourseWebDto registrationForCourse(@RequestBody RegistrationWebDto registrationWebDto) {
        Registration registration = registrationMapper.toEntity(registrationWebDto);
        Course course = businessCourseService.findCourseAndAddStudent(registration);
        return courseMapper.toWebDTO(course);
    }


    @PostMapping("/create")
    public CourseWebDto create(@RequestBody CourseWebDto courseWebDto) {
        Course course = courseMapper.toEntityFromWebDto(courseWebDto);
        Course courseCreate = businessCourseService.create(course);
        return courseMapper.toWebDTO(courseCreate);
    }

    @PostMapping("/create/by/category")
    public List<CourseWebDto> courseByCategory(@RequestParam Integer numberCategory) {
        List<Course> courseList = businessCourseService.coursesByCategory(numberCategory);

        return courseList.stream()
                .map(courseMapper::toWebDTO)
                .toList();
    }

    @PostMapping("/create/by/category/name")
    public List<CourseWebDto> courseByCategoryName(@RequestParam String category) {
        List<Course> courseList = businessCourseService.coursesByCategoryName(category);

        return courseList.stream()
                .map(courseMapper::toWebDTO)
                .toList();
    }

    @PostMapping("/create/by/location")
    public List<CourseWebDto> courseByLocation(String location) {
        List<Course> courseList = businessCourseService.coursesByLocation(location);

        List<CourseWebDto> listCourseWebDtos = courseList.stream()
                .map(courseMapper::toWebDTO)
                .toList();

        return listCourseWebDtos;
    }

    @PostMapping("/create/by/teacher")
    public List<CourseWebDto> courseByTeacher(String teacherSurname) {
        List<Course> courseList = businessCourseService.coursesByTeacher(teacherSurname);

        return courseList.stream()
                .map(courseMapper::toWebDTO)
                .toList();
    }

    @PostMapping("/create/by/price")
    public List<CourseWebDto> courseByPrice(@RequestParam Integer price) {
        List<Course> courseList = businessCourseService.coursesByPrice(price);

        return courseList.stream()
                .map(courseMapper::toWebDTO)
                .toList();
    }

    @PostMapping("/create/by/duration")
    public List<CourseWebDto> courseByDuration(@RequestParam Integer duration) {
        List<Course> courseList = businessCourseService.coursesByDuration(duration);

        return courseList.stream()
                .map(courseMapper::toWebDTO)
                .toList();
    }

    @DeleteMapping("/delete/{number}")
    public CourseDto deleteCourse(@PathVariable Integer number) {
        return courseMapper.toDTO(businessCourseService.deleteCourse(number));
    }

    @GetMapping("/read/all")
    public List<CourseWebDto> readAll() {
        List<Course> courseDtoList = businessCourseService.readAll();

        return courseDtoList.stream()
                .map(courseMapper::toWebDTO)
                .toList();
    }

    @GetMapping("/read/teacher")
    public TeacherWebDto teacherBySurname(String surname) {
        Teacher teacher = businessCourseService.teacherBySurname(surname);

        return teacherMapper.toWebDto(teacher);
    }

    @PostMapping("/recruiting/create")
    public Recruiting createRecruiting(@RequestBody Recruiting recruiting) {

        return businessCourseService.createRecruiting(recruiting);
    }

    @PostMapping("/filter/by/completion")
    public List<CourseWebDto> studentCompletedCourse(@RequestParam String surname) {
        List<Course> courseList = businessStudentService.studentCompletedCourses(surname);

        return courseList.stream()
                .map(courseMapper::toWebDTO)
                .toList();
    }

    @PostMapping("/filter/by/upcoming")
    public List<CourseWebDto> studentUpcomingCourse(@RequestParam String surname) {
        List<Course> courseList = businessStudentService.studentUpcomingCourses(surname);

        return courseList.stream()
                .map(courseMapper::toWebDTO)
                .toList();
    }

    @GetMapping("/read/student")
    public StudentWebDto studentBySurname(String surname) {
        Student student = businessStudentService.readStudent(surname);

        return studentMapper.toWebDto(student);
    }

    @GetMapping("/read/student/by/login")
    public StudentWebDto studentByLogin(@RequestParam String login) {
        Student student = businessStudentService.findByLogin(login);

        return studentMapper.toWebDto(student);
    }
}
