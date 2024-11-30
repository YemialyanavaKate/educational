package by.ita.je.controllers;


import by.ita.je.dto.StudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/database/student")
public class StudentController {

    @PostMapping("/create")
    public StudentDto create(){
       return StudentDto.builder()
               .name("Ivan")
               .number(10)
               .build();
    }

    @GetMapping("/read")
    public StudentDto read(){
        return StudentDto.builder()
                .name("Anne")
                .number(11)
                .build();
    }

    @GetMapping("/read/all")
    public List<StudentDto> readAll(){
        StudentDto courseDto1 = StudentDto.builder()
                .name("Ivan")
                .number(10)
                .build();
        StudentDto courseDto2 = StudentDto.builder()
                .name("Anne")
                .number(11)
                .build();
        List<StudentDto> list = new ArrayList<>();
        list.add(courseDto1);
        list.add(courseDto2);
        return list;
    }

    @PutMapping("/update")
    public StudentDto update(){
        return StudentDto.builder()
                .name("Ivan+")
                .number(10)
                .build();
    }
    @DeleteMapping("/delete")
    public StudentDto delete(){
        return StudentDto.builder()
                .name("Ivan+")
                .number(10)
                .build();
    }
    @DeleteMapping("/delete/all")
    public List<StudentDto> deleteAll(){
        return Collections.emptyList();
    }
}
