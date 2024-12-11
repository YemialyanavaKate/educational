package by.ita.je.service;

import by.ita.je.dto.to_data_base.CourseDto;
import by.ita.je.mappers.CourseMapper;
import by.ita.je.mappers.StudentMapper;
import by.ita.je.models.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BusinessStudentService {
    public static final String ROOT_COURSE = "http://localhost:8101/database/course";
    public static final String ROOT_STUDENT = "http://localhost:8101/database/student";

    public static final String METOD_READ = "/read/";
    public static final String METOD_READ_ALL = "/read/all";
    public static final String METOD_CREATE = "/create";
    public static final String METOD_UPDATE = "/update";
    public static final String METOD_DELETE = "/delete";
    public static final String METOD_FILTER_CATEGORY_NAME = "/filter/category/name?category=";
    public static final String METOD_FILTER_PRICE = "/filter/price?price=";
    public static final String METOD_FILTER_COMPLETED_COURSES = "/filter/course/completed?surname=";
    public static final String METOD_FILTER_UPCOMING_COURSES = "/filter/course/upcoming?surname=";


    private final RestTemplate serviceAppRestClient;
    private final CourseMapper courseMapper;
    private final StudentMapper studentMapper;

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
}
