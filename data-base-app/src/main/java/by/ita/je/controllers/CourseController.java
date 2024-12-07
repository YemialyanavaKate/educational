package by.ita.je.controllers;


import by.ita.je.dto.CourseDto;
import by.ita.je.mappers.CourseMapper;
import by.ita.je.models.Course;
import by.ita.je.services.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/database/course")
public class CourseController {
    private final CourseService courseService;
    private final CourseMapper courseMapper;

    @PostMapping("/create")
    public CourseDto create(@RequestBody CourseDto courseDto) {
        Course course = courseMapper.toEntity(courseDto);
        Course courseCreate = courseService.insert(course);
        return courseMapper.toDTO(courseCreate);
    }

    @GetMapping("/read/{number}")
    public CourseDto read(@PathVariable(name = "number") Integer number) {
        return courseMapper.toDTO(courseService.findByNumber(number));
    }

    @GetMapping("/read/all")
    public List<CourseDto> readAll() {
        return courseService.readAll().stream().map(courseMapper::toDTO).toList();
    }

    @PutMapping("/update")
    public CourseDto update(@RequestBody CourseDto courseDto) {
        Course course = courseMapper.toEntity(courseDto);
        return courseMapper.toDTO(courseService.update(course));
    }

    @DeleteMapping("/delete/{number}")
    public CourseDto delete(@PathVariable(name = "number") Integer number) {
        return courseMapper.toDTO(courseService.delete(number));
    }

    @DeleteMapping("/delete/all")
    public List<CourseDto> deleteAll() {
        return courseService.deleteAll().stream().map(courseMapper::toDTO).toList();
    }

    @GetMapping("/filter/category")
    public List<CourseDto> filterCategory(@RequestParam Integer numberCategory) {
        return courseService.findByCategory(numberCategory).stream().map(courseMapper::toDTO).toList();
    }

    @GetMapping("/filter/price")
    public List<CourseDto> filterPrice(@RequestParam Integer price) {
        return courseService.findByPrice(price).stream().map(courseMapper::toDTO).toList();
    }

    @GetMapping("/filter/duration")
    public List<CourseDto> filterDuration(@RequestParam Integer duration) {
        return courseService.findByDuration(duration).stream().map(courseMapper::toDTO).toList();
    }
}
