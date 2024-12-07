package by.ita.je.service;

import by.ita.je.models.Course;
import by.ita.je.models.Registration;
import by.ita.je.models.Student;
import by.ita.je.models.Teacher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class BusinessCourseIT {
    @Autowired
    private BusinessCourseService businessCourseService;

    @Test
    public void contextTest(){
        Assertions.assertNotNull(businessCourseService);
    }

    @Test
    public void create_then_correctCourse(){
        Teacher teacher = Teacher.builder()
                .number(3)
                .name("Jon")
                .surname("Li")
                .role("teacher")
                .build();

        Course testCourse = Course.builder()
                .number(5)
                .name("Manager_best")
                .location("Minsk")
                .price(BigDecimal.valueOf(5000))
                .duration(4)
                .students(Collections.emptyList())
                .teacher(teacher)
                .build();
        Course actual = businessCourseService.create(testCourse);
        assertEquals(actual, testCourse);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void findCourseAndAddStudent(){
        Student student = Student.builder()
                .number(1)
                .name("Anne")
                .surname("Fox")
                .login("Anne")
                .password("123")
                .role("student")
                .balance(BigDecimal.valueOf(8000))
                .build();
        List<Student> studentList = Collections.singletonList(student);
        Course testCourse = Course.builder()
                .number(4)
                .name("Manager_core")
                .location("Minsk")
                .price(BigDecimal.valueOf(1000))
                .duration(3)
                .students(studentList)
                .build();

        Registration registration = Registration.builder()
                .number(4)
                .name("Anne")
                .surname("Fox")
                .build();

        Course actual = businessCourseService.findCourseAndAddStudent(registration);
        assertEquals(actual.getNumber(), testCourse.getNumber());
        assertEquals(actual.getName(), testCourse.getName());
        assertEquals(actual.getStudents(), testCourse.getStudents());
    }

    @Test
    public void coursesByCategory(){
        Course testCourse = Course.builder()
                .number(4)
                .name("Manager_core")
                .location("Minsk")
                .price(BigDecimal.valueOf(1000))
                .duration(3)
                .build();
        List<Course> actualList = businessCourseService.coursesByCategory(2);
        assertEquals(actualList.get(0).getNumber(), testCourse.getNumber());
        assertEquals(actualList.get(0).getName(), testCourse.getName());
        assertEquals(1, actualList.size());
    }

    @Test
    public void coursesByTeacher(){
        Course testCourse1 = Course.builder()
                .number(2)
                .name("JAVA_ENTERPRISE")
                .location("Brest")
                .price(BigDecimal.valueOf(3000))
                .duration(5)
                .build();
        Course testCourse2 = Course.builder()
                .number(4)
                .name("Manager_core")
                .location("Minsk")
                .price(BigDecimal.valueOf(1000))
                .duration(3)
                .build();

        List<Course> actualList = businessCourseService.coursesByTeacher("Adams");
        assertEquals(actualList.get(0).getNumber(), testCourse1.getNumber());
        assertEquals(actualList.get(1).getNumber(), testCourse2.getNumber());
        assertEquals(actualList.get(0).getName(), testCourse1.getName());
        assertEquals(actualList.get(1).getName(), testCourse2.getName());
        assertEquals(2, actualList.size());
    }

    @Test
    public void coursesByLocation(){
        Course testCourse = Course.builder()
                .number(2)
                .name("JAVA_ENTERPRISE")
                .location("Brest")
                .price(BigDecimal.valueOf(3000))
                .duration(5)
                .build();
        List<Course> actualList = businessCourseService.coursesByLocation("Brest");
        assertEquals(actualList.get(0).getNumber(), testCourse.getNumber());
        assertEquals(actualList.get(0).getName(), testCourse.getName());
        assertEquals(1, actualList.size());
    }

    @Test
    public void coursesByPrice(){
        Course testCourse = Course.builder()
                .number(4)
                .name("Manager_core")
                .location("Minsk")
                .price(BigDecimal.valueOf(1000))
                .duration(3)
                .build();
        List<Course> actualList = businessCourseService.coursesByPrice(1000);
        assertEquals(actualList.get(0).getNumber(), testCourse.getNumber());
        assertEquals(actualList.get(0).getName(), testCourse.getName());
        assertEquals(1, actualList.size());
    }

    @Test
    public void coursesByDuration(){
        Course testCourse = Course.builder()
                .number(4)
                .name("Manager_core")
                .location("Minsk")
                .price(BigDecimal.valueOf(1000))
                .duration(3)
                .build();
        List<Course> actualList = businessCourseService.coursesByDuration(3);
        assertEquals(actualList.get(0).getNumber(), testCourse.getNumber());
        assertEquals(actualList.get(0).getName(), testCourse.getName());
        assertEquals(1, actualList.size());
    }

    @Test
    public void deleteCourse(){
        Course testCourse = Course.builder()
                .number(3)
                .name("JAVA_SUPER")
                .location("Minsk")
                .price(BigDecimal.valueOf(3000))
                .duration(5)
                .build();
        Course actual = businessCourseService.deleteCourse(3);
        assertEquals(actual.getNumber(), testCourse.getNumber());
        assertEquals(actual.getName(), testCourse.getName());
        assertEquals(actual.getDuration(), testCourse.getDuration());

    }
}
