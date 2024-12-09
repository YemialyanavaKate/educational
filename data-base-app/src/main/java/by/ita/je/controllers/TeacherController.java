package by.ita.je.controllers;


import by.ita.je.dto.TeacherDto;
import by.ita.je.mappers.TeacherMapper;
import by.ita.je.models.Teacher;
import by.ita.je.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/database/teacher")
public class TeacherController {

    private final TeacherService teacherService;
    private final TeacherMapper teacherMapper;

    @PostMapping("/create")
    public TeacherDto create(@RequestBody TeacherDto teacherDto) {
        Teacher teacher = teacherMapper.toEntity(teacherDto);
        Teacher teacherCreate = teacherService.insert(teacher);
        return teacherMapper.toDto(teacherCreate);
    }

    @GetMapping("/read/{number}")
    public TeacherDto read(@PathVariable(name = "number") Integer number) {
        return teacherMapper.toDto(teacherService.findByNumber(number));
    }

    @GetMapping("/read/all")
    public List<TeacherDto> readAll() {
        return teacherService.readAll().stream().map(teacherMapper::toDto).toList();
    }

    @PutMapping("/update")
    public TeacherDto update(@RequestBody TeacherDto teacherDto) {
        Teacher teacher = teacherMapper.toEntity(teacherDto);
        return teacherMapper.toDto(teacherService.update(teacher));
    }

    @DeleteMapping("/delete/{number}")
    public TeacherDto delete(@PathVariable(name = "number") Integer number) {
        return teacherMapper.toDto(teacherService.delete(number));
    }

    @DeleteMapping("/delete/all")
    public List<TeacherDto> deleteAll() {
        return teacherService.deleteAll().stream().map(teacherMapper::toDto).toList();
    }

    @GetMapping("/filter/surname")
    public List<TeacherDto> filterSurname(@RequestParam String surname) {
        return teacherService.findByTeacherSurname(surname).stream().map(teacherMapper::toDto).toList();
    }

}
