package by.ita.je.controllers;


import by.ita.je.dto.StudentDto;
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
}
