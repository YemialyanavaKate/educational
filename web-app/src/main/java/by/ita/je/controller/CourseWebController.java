package by.ita.je.controller;

import by.ita.je.dto.CourseWebDto;
import by.ita.je.mappers.CourseMapper;
import by.ita.je.models.Course;
import by.ita.je.service.CourseWebService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseWebController {
    private final CourseWebService courseWebService;
    private final CourseMapper courseMapper;


    @GetMapping("/create")
    public String showCreateCourseForm(Model model) {
        model.addAttribute("course", new CourseWebDto());
        return "createCourse.html";
    }

    @PostMapping("/create")
    public String createCourse(@ModelAttribute CourseWebDto courseWebDto, Model model) {
        Course course = courseMapper.toEntityFromWebDto(courseWebDto);
        Course createCourse = courseWebService.createCourse(course);
        CourseWebDto courseCreateWebDto = courseMapper.toWebDTO(createCourse);
        model.addAttribute("course", courseCreateWebDto);
        return "successCreateCourse.html";
    }
}
