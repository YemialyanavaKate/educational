package by.ita.je.controllers;


import by.ita.je.dto.TeacherDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/database/teacher")
public class TeacherController {

    @PostMapping("/create")
    public TeacherDto create(){
       return TeacherDto.builder()
               .name("Tom")
               .number(10)
               .build();
    }

    @GetMapping("/read")
    public TeacherDto read(){
        return TeacherDto.builder()
                .name("Eva")
                .number(11)
                .build();
    }

    @GetMapping("/read/all")
    public List<TeacherDto> readAll(){
        TeacherDto courseDto1 = TeacherDto.builder()
                .name("Tom")
                .number(10)
                .build();
        TeacherDto courseDto2 = TeacherDto.builder()
                .name("Eva")
                .number(11)
                .build();
        List<TeacherDto> list = new ArrayList<>();
        list.add(courseDto1);
        list.add(courseDto2);
        return list;
    }

    @PutMapping("/update")
    public TeacherDto update(){
        return TeacherDto.builder()
                .name("Tom+")
                .number(10)
                .build();
    }
    @DeleteMapping("/delete")
    public TeacherDto delete(){
        return TeacherDto.builder()
                .name("Tom+")
                .number(10)
                .build();
    }
    @DeleteMapping("/delete/all")
    public List<TeacherDto> deleteAll(){
        return Collections.emptyList();
    }
}
