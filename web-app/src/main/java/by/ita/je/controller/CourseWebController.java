package by.ita.je.controller;

import by.ita.je.dto.CourseWebDto;
import by.ita.je.dto.StudentWebDto;
import by.ita.je.dto.TeacherWebDto;
import by.ita.je.mappers.CourseMapper;
import by.ita.je.models.Course;
import by.ita.je.models.Recruiting;
import by.ita.je.service.CourseWebService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseWebController {
    private final CourseWebService courseWebService;
    private final CourseMapper courseMapper;

    @GetMapping("/home")
    public String showHomePage() {
        return "homePage.html";
    }

    @GetMapping("/teacher")
    public String showTeacherPage() {
        return "teacherPage.html";
    }

    @GetMapping("/lastname")
    public String showFilterCourseByTeacher(Model model) {
        model.addAttribute("teacher", new TeacherWebDto());
        return "teacherLastName.html";
    }

    @PostMapping("/filter/by/teacher")
    public String showFilterCourseByTeacher(String surname, Model model) {
        //TeacherWebDto teacher = (TeacherWebDto) model.getAttribute("teacher");
        List<CourseWebDto> courseWebDtoList = courseWebService.filterByTeacher(surname)
                .stream()
                .map(courseMapper::toWebDTO)
                .toList();
        model.addAttribute("courses", courseWebDtoList);
        return "teacherCoursesPage.html";
    }

    @GetMapping("/read/all")
    public String showAllCourse(Model model) {
        List<CourseWebDto> courseWebDtoList = courseWebService.readeAllCourse()
                .stream()
                .map(courseMapper::toWebDTO)
                .toList();
        model.addAttribute("courses", courseWebDtoList);
        return "allCourse.html";
    }


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

    @GetMapping("/create/recruiting")
    public String showCreateRecruitingForm(Model model) {
        model.addAttribute("recruiting", new Recruiting());
        return "recruitingCourse.html";
    }

    @PostMapping("/create/recruiting")
    public String CreateRecruiting(@ModelAttribute Recruiting recruiting, Model model) {
        Recruiting createRecruiting = courseWebService.createRecruiting(recruiting);
        model.addAttribute("recruiting", createRecruiting);
        return "successRecruitingPage.html";
    }

    @GetMapping("/delete")
    public String showDeleteForm(Model model) {
        List<CourseWebDto> courseWebDtoList = courseWebService.readeAllCourse()
                .stream()
                .map(courseMapper::toWebDTO)
                .toList();
        model.addAttribute("courses", courseWebDtoList);
        return "deleteCourse.html";
    }

    @PostMapping("/delete")
    public String showDeleteResult(Integer number) {
        courseWebService.deleteCourse(number);
        return "deleteSuccess.html";
    }

    @GetMapping("/student")
    public String showStudentPage() {
        return "studentPage.html";
    }

    @GetMapping("/lastname/student/completed")
    public String showFilterCourseByCompletedForStudent(Model model) {
        model.addAttribute("student", new StudentWebDto());
        return "studentLastNameForCompletedCourses.html";
    }

    @PostMapping("/filter/by/completed")
    public String showFilterCompletedCourse(String surname, Model model) {
        List<CourseWebDto> courseWebDtoList = courseWebService.filterCompletedCourse(surname)
                .stream()
                .map(courseMapper::toWebDTO)
                .toList();
        model.addAttribute("courses", courseWebDtoList);
        return "completedCourses.html";
    }

    @GetMapping("/lastname/student/upcoming")
    public String showFilterCourseByUpcomingForStudent(Model model) {
        model.addAttribute("student", new StudentWebDto());
        return "studentLastNameForUpcomingCourses.html";
    }

    @PostMapping("/filter/by/upcoming")
    public String showFilterUpcomingCourse(String surname, Model model) {
        List<CourseWebDto> courseWebDtoList = courseWebService.filterUpcomingCourse(surname)
                .stream()
                .map(courseMapper::toWebDTO)
                .toList();
        model.addAttribute("courses", courseWebDtoList);
        return "upcomingCourses.html";
    }

    @GetMapping("/lastname/home")
    public String showFilterCourseByTeacherFromHome(Model model) {
        model.addAttribute("teacher", new TeacherWebDto());
        return "teacherLastNameFromHome.html";
    }

    @GetMapping("/category/name")
    public String showFilterCourseByCategoryName(Model model) {
        model.addAttribute("course", new CourseWebDto());
        return "categoryName.html";
    }
    @PostMapping("/category/name")
    public String FilterCourseCategoryName(String name, Model model) {
        String category = name;
        List<CourseWebDto> courseWebDtoList = courseWebService.filterByCategoryName(category)
                .stream()
                .map(courseMapper::toWebDTO)
                .toList();
        model.addAttribute("courses", courseWebDtoList);
        return "allCourse.html";
    }

    @PostMapping("/filter/by/teacher/home")
    public String showFilterCourseByTeacherFromHome(String surname, Model model) {
        List<CourseWebDto> courseWebDtoList = courseWebService.filterByTeacher(surname)
                .stream()
                .map(courseMapper::toWebDTO)
                .toList();
        model.addAttribute("courses", courseWebDtoList);
        return "allCourse.html";
    }
}
