package by.ita.je.controller;

import by.ita.je.dto.CourseWebDto;
import by.ita.je.dto.RegistrationWebDto;
import by.ita.je.dto.StudentWebDto;
import by.ita.je.dto.TeacherWebDto;
import by.ita.je.mappers.CourseMapper;
import by.ita.je.mappers.RegistrationMapper;
import by.ita.je.mappers.StudentMapper;
import by.ita.je.models.Course;
import by.ita.je.models.Recruiting;
import by.ita.je.models.Registration;
import by.ita.je.models.Student;
import by.ita.je.service.CourseWebService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseWebController {
    private final CourseWebService courseWebService;
    private final CourseMapper courseMapper;
    private final RegistrationMapper registrationMapper;
    private final StudentMapper studentMapper;

    @GetMapping("/home")
    public String showHomePage() {
        return "homePage.html";
    }

    @GetMapping("/teacher")
    @PreAuthorize("hasAuthority('teacher')")
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
    @PreAuthorize("hasAuthority('student')")
    public String showStudentPage() {
        return "studentPage.html";
    }

    /*@GetMapping("/lastname/student/completed")
    public String showFilterCourseByCompletedForStudent(Model model) {
        model.addAttribute("student", new StudentWebDto());
        return "studentLastNameForCompletedCourses.html";
    }*/

    @GetMapping("/lastname/student/completed")
    public String showFilterCompletedCourse(Model model, Principal principal) {
        String surname = principal.getName();
        List<CourseWebDto> courseWebDtoList = courseWebService.filterCompletedCourse(surname)
                .stream()
                .map(courseMapper::toWebDTO)
                .toList();
        model.addAttribute("courses", courseWebDtoList);
        return "completedCourses.html";
    }

    /*@GetMapping("/lastname/student/upcoming")
    public String showFilterCourseByUpcomingForStudent(Model model) {
        model.addAttribute("student", new StudentWebDto());
        return "studentLastNameForUpcomingCourses.html";
    }
*/
    @GetMapping("/lastname/student/upcoming")
    public String showFilterUpcomingCourse(Model model, Principal principal) {
        String surname = principal.getName();
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

    @GetMapping("/add/student")
    public String showAddStudentToCourseForm(Model model) {
        model.addAttribute("registration", new RegistrationWebDto());
        return "addStudentToCourse.html";
    }

    @PostMapping("/add/student")
    public String AddStudentToCourse(@ModelAttribute RegistrationWebDto registrationWebDto, Model model) {
        Registration registration = registrationMapper.toEntity(registrationWebDto);
        Course courseWithStudent = courseWebService.addStudentToCourse(registration);
        CourseWebDto courseWithStudentWebDto = courseMapper.toWebDTO(courseWithStudent);
        model.addAttribute("course", courseWithStudentWebDto);
        return "successAddStudentToCourse.html";
    }

   /* @GetMapping("/read/student")
    public String showFormForSurname(Model model) {
        model.addAttribute("student", new StudentWebDto());
        return "forStudentSurname.html";
    }*/

    @GetMapping("/read/student")
    public String showStudentProfile (Model model, Principal principal) {
        String surname = principal.getName();
        Student student = courseWebService.readStudentBySurName(surname);
        StudentWebDto studentWebDto = studentMapper.toWebDto(student);
        model.addAttribute("student", studentWebDto);
        return "studentBySurname.html";
    }

    /*@GetMapping("/logout")
    public String showLogoutPage (Model model, Principal principal) {
        String surname = principal.getName();
        Student student = courseWebService.readStudentBySurName(surname);
        StudentWebDto studentWebDto = studentMapper.toWebDto(student);
        model.addAttribute("student", studentWebDto);
        return "studentBySurname.html";
    }*/

    @GetMapping("/logout")
    private String removeCookies(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        Cookie rememberMeCookie = new Cookie("remember-me", "");

        rememberMeCookie.setMaxAge(0);

        response.addCookie(rememberMeCookie);

        request.logout();

        return "homePage.html";
    }
}
