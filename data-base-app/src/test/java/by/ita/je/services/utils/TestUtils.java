package by.ita.je.services.utils;

import by.ita.je.models.Course;
import by.ita.je.models.Student;
import by.ita.je.models.Teacher;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public abstract class TestUtils {
    protected Course buildCourse(Integer number, String name, String location, BigDecimal price, Integer duration, ZonedDateTime start, ZonedDateTime stop){
        return Course.builder()
                .number(number)
                .name(name)
                .location(location)
                .price(price)
                .duration(duration)
                .start(start)
                .stop(stop)
        .build();
    }

    protected Teacher buildTeacher(Integer number, String name, String surname, String login, String password, String role){
        return Teacher.builder()
                .number(number)
                .name(name)
                .surname(surname)
                .login(login)
                .password(password)
                .role(role)
                .build();
    }

    protected Student buildStudent(Integer number, String name, String surname, String login, String password, String role){
        return Student.builder()
                .number(number)
                .name(name)
                .surname(surname)
                .login(login)
                .password(password)
                .role(role)
                .build();
    }
}
