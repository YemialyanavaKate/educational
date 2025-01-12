package by.ita.je.controllers;


import by.ita.je.dto.CourseDto;
import by.ita.je.dto.StudentDto;
import by.ita.je.mappers.CourseMapper;
import by.ita.je.mappers.StudentMapper;
import by.ita.je.models.Student;
import by.ita.je.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/database/student")
public class StudentController {
    private final StudentService studentService;
    private final StudentMapper studentMapper;
    private final CourseMapper courseMapper;

    @PostMapping("/create")
    public StudentDto create(@RequestBody StudentDto studentDto) {
        Student student = studentMapper.toEntity(studentDto);
        Student studentCreate = studentService.insert(student);
        return studentMapper.toDto(studentCreate);
    }

    @GetMapping("/read/{number}")
    public StudentDto read(@PathVariable(name = "number") Integer number) {
        return studentMapper.toDto(studentService.findByNumber(number));
    }

    @GetMapping("/read/by/login/{login}")
    public StudentDto readByLogin(@PathVariable(name = "login") String login) {
        return studentMapper.toDto(studentService.findByLogin(login));
    }
    @GetMapping("/read/by/surname/{surname}")
    public StudentDto readBuSurname(@PathVariable(name = "surname") String surname) {
        return studentMapper.toDto(studentService.findBySurname(surname));
    }

   /* @GetMapping("/read/by/surname/new/{surname}")
    public StudentDto readBuSurnameNew(@PathVariable(name = "surname") String surname) {
        return studentMapper.toDto(studentService.findBySurnameNew(surname));
    }
*/
    @GetMapping("/read/all")
    public List<StudentDto> readAll() {
        return studentService.readAll().stream().map(studentMapper::toDto).toList();
    }

    @PutMapping("/update")
    public StudentDto update(@RequestBody StudentDto studentDto) {
        Student student = studentMapper.toEntity(studentDto);
        return studentMapper.toDto(studentService.update(student));
    }

    @DeleteMapping("/delete/{number}")
    public StudentDto delete(@PathVariable(name = "number") Integer number) {
        return studentMapper.toDto(studentService.delete(number));
    }

    @DeleteMapping("/delete/all")
    public List<StudentDto> deleteAll() {
        return studentService.deleteAll().stream().map(studentMapper::toDto).toList();
    }

    @GetMapping("/filter/course")
    public List<StudentDto> findByCourse(Integer number) {
        return studentService.findAllStudentsByCourse(number).stream().map(studentMapper::toDto).toList();
    }

    @GetMapping("/filter/course/completed")
    public List<CourseDto> findCompletedCourses(String surname) {
        return studentService.findCompletedCourses(surname).stream().map(courseMapper::toDTO).toList();
    }

    @GetMapping("/filter/course/upcoming")
    public List<CourseDto> findUpcomingCourses(String surname) {
        return studentService.findUpcomingCourses(surname).stream().map(courseMapper::toDTO).toList();
    }
}
