package by.ita.je.service;

import by.ita.je.dto.to_data_base.CourseDto;
import by.ita.je.dto.to_data_base.StudentDto;
import by.ita.je.mappers.CourseMapper;
import by.ita.je.mappers.StudentMapper;
import by.ita.je.models.Course;
import by.ita.je.models.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BusinessStudentService {
    public static final String ROOT_STUDENT = "http://localhost:8101/database/student";

    public static final String METOD_READ = "/read/by/surname/";
    public static final String METOD_READ_BY_LOGIN = "/read/by/login/";

    public static final String METOD_FILTER_COMPLETED_COURSES = "/filter/course/completed?surname=";
    public static final String METOD_FILTER_UPCOMING_COURSES = "/filter/course/upcoming?surname=";


    private final RestTemplate serviceAppRestClient;
    private final CourseMapper courseMapper;
    private final StudentMapper studentMapper;

    public Student readStudent(String surname) throws HttpServerErrorException.InternalServerError {
        String urlRedStudent = String.format("%s%s%s", ROOT_STUDENT, METOD_READ, surname);
        return studentMapper.toEntityFromDataBase(serviceAppRestClient.getForObject(urlRedStudent, StudentDto.class));
    }

    public List<Course> studentCompletedCourses(String surname) {
        String urlFilterCompletedCourses = String.format("%s%s%s", ROOT_STUDENT, METOD_FILTER_COMPLETED_COURSES, surname);

        ResponseEntity<List<CourseDto>> responseEntity = serviceAppRestClient.exchange(urlFilterCompletedCourses, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });

        List<CourseDto> listCourseDto = responseEntity.getBody() != null ?
                responseEntity.getBody().stream().toList() : Collections.emptyList();
        return listCourseDto.stream()
                .map(courseMapper::toEntity)
                .toList();
    }

    public List<Course> studentUpcomingCourses(String surname) {
        String urlFilterUpcomingCourses = String.format("%s%s%s", ROOT_STUDENT, METOD_FILTER_UPCOMING_COURSES, surname);

        ResponseEntity<List<CourseDto>> responseEntity = serviceAppRestClient.exchange(urlFilterUpcomingCourses, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });

        List<CourseDto> listCourseDto = responseEntity.getBody() != null ?
                responseEntity.getBody().stream().toList() : Collections.emptyList();
        return listCourseDto.stream()
                .map(courseMapper::toEntity)
                .toList();
    }

    public Student findByLogin(String login) throws HttpServerErrorException.InternalServerError {
        String urlRedStudent = String.format("%s%s%s", ROOT_STUDENT, METOD_READ_BY_LOGIN, login);
        return studentMapper.toEntityFromDataBase(serviceAppRestClient.getForObject(urlRedStudent, StudentDto.class));
    }
}
