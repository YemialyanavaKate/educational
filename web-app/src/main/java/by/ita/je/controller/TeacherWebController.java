package by.ita.je.controller;

import by.ita.je.dto.TeacherWebDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher")
public class TeacherWebController {

    @GetMapping("/home")
    public String teacherHomePage(Model model) {
        return "teacherPage.html";
    }

    @GetMapping("/lastname")
    public String showFilterCourseByTeacher(Model model) {
        model.addAttribute("teacher", new TeacherWebDto());
        return "teacherLastName.html";
    }

    @PostMapping("/lastname")
    public String filterCourseByTeacher(Model model) {
        model.addAttribute("teacher", new TeacherWebDto());
        return "teacherCoursesPage.html";
    }


}
