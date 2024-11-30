package by.ita.je.controllers;


import by.ita.je.dto.CategoryDto;
import by.ita.je.dto.CourseDto;
import by.ita.je.mappers.CategoryConverter;
import by.ita.je.mappers.CategoryMapper;
import by.ita.je.models.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/database/course")
public class CourseController {

    @PostMapping("/create")
    public CourseDto create(){
       return CourseDto.builder()
               .name("java")
               .number(10)
               .build();
    }

    @GetMapping("/read")
    public CourseDto read(){
        return CourseDto.builder()
                .name("IT_HR")
                .number(11)
                .build();
    }

    @GetMapping("/read/all")
    public List<CourseDto> readAll(){
        CourseDto courseDto1 = CourseDto.builder()
                .name("java")
                .number(10)
                .build();
        CourseDto courseDto2 = CourseDto.builder()
                .name("IT_HR")
                .number(11)
                .build();
        List<CourseDto> list = new ArrayList<>();
        list.add(courseDto1);
        list.add(courseDto2);
        return list;
    }

    @PutMapping("/update")
    public CourseDto update(){
        return CourseDto.builder()
                .name("java+")
                .number(10)
                .build();
    }
    @DeleteMapping("/delete")
    public CourseDto delete(){
        return CourseDto.builder()
                .name("java")
                .number(10)
                .build();
    }
    @DeleteMapping("/delete/all")
    public List<CourseDto> deleteAll(){
        return Collections.emptyList();
    }
}
