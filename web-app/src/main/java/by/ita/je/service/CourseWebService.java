package by.ita.je.service;

import by.ita.je.dto.CourseWebDto;
import by.ita.je.mappers.CourseMapper;
import by.ita.je.models.Course;
import by.ita.je.models.Recruiting;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseWebService {
    private final RestTemplate serviceAppRestClient;
    private final CourseMapper courseMapper;

    public static final String ROOT_COURSE = "/business/course";
    public static final String ROOT_STUDENT = "/business/student";
    public static final String ROOT_TEACHER = "/business/teacher";

    public static final String METHOD_CREATE = "/create";
    public static final String METHOD_READ_ALL = "/read/all";
    public static final String METHOD_FILTER_BY_TEACHER = "/create/by/teacher?teacherSurname=";
    public static final String METHOD_READ_TEACHER = "/read/teacher?surname=";
    public static final String METHOD_CREATE_RECRUITING = "/recruiting/create";
    public static final String METHOD_DELETE = "/delete/";
    public static final String METHOD_FILTER_COMPLETED_COURSES = "/filter/by/completion?surname=";
    public static final String METHOD_FILTER_UPCOMING_COURSES = "/filter/by/upcoming?surname=";
    public static final String METHOD_FILTER_CATEGORY_COURSES = "/create/by/category/name?category=";


    public Course createCourse(Course course) {

        String surname = course.getTeacher().getSurname();
        String urlReadTeachers = String.format("%s%s%s", ROOT_COURSE, METHOD_READ_TEACHER, surname);

        CourseWebDto courseWebDto = courseMapper.toWebDTO(course);
        //courseWebDto.setTeacher(teacherWebDto);
        String urlCreate = String.format("%s%s", ROOT_COURSE, METHOD_CREATE);
        return courseMapper.toEntityFromWebDto(serviceAppRestClient.postForObject(urlCreate, courseWebDto, CourseWebDto.class));
    }

    public List<Course> readeAllCourse() {
        String urlReadALLCourse = String.format("%s%s", ROOT_COURSE, METHOD_READ_ALL);
        ResponseEntity<List<CourseWebDto>> responseEntity = serviceAppRestClient.exchange(urlReadALLCourse, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
        List<CourseWebDto> courseWebDtoList = responseEntity.getBody() != null ?
                responseEntity.getBody().stream().toList() : Collections.emptyList();
        return courseWebDtoList.stream()
                .map(courseMapper::toEntityFromWebDto)
                .toList();
    }

    public List<Course> filterByTeacher(String surname) {
        String urlReadALLCourse = String.format("%s%s%s", ROOT_COURSE, METHOD_FILTER_BY_TEACHER, surname);
        ResponseEntity<List<CourseWebDto>> responseEntity = serviceAppRestClient.exchange(urlReadALLCourse, HttpMethod.POST, null, new ParameterizedTypeReference<>() {
        });
        List<CourseWebDto> courseWebDtoList = responseEntity.getBody() != null ?
                responseEntity.getBody().stream().toList() : Collections.emptyList();
        return courseWebDtoList.stream()
                .map(courseMapper::toEntityFromWebDto)
                .toList();
    }

    public Recruiting createRecruiting(Recruiting recruiting) {
        String urlCreateRecruiting = String.format("%s%s", ROOT_COURSE, METHOD_CREATE_RECRUITING);
        return serviceAppRestClient.postForObject(urlCreateRecruiting, recruiting, Recruiting.class);
    }

    public void deleteCourse(Integer number) {
        String urlDeleteCourse = String.format("%s%s%d", ROOT_COURSE, METHOD_DELETE, number);
        serviceAppRestClient.delete(urlDeleteCourse);
    }

    public List<Course> filterCompletedCourse(String surname) {
        String urlReadCourses = String.format("%s%s%s", ROOT_COURSE, METHOD_FILTER_COMPLETED_COURSES, surname);
        ResponseEntity<List<CourseWebDto>> responseEntity = serviceAppRestClient.exchange(urlReadCourses, HttpMethod.POST, null, new ParameterizedTypeReference<>() {
        });
        List<CourseWebDto> courseWebDtoList = responseEntity.getBody() != null ?
                responseEntity.getBody().stream().toList() : Collections.emptyList();
        return courseWebDtoList.stream()
                .map(courseMapper::toEntityFromWebDto)
                .toList();
    }

    public List<Course> filterUpcomingCourse(String surname) {


        String urlReadCourses = String.format("%s%s%s", ROOT_COURSE, METHOD_FILTER_UPCOMING_COURSES, surname);
        ResponseEntity<List<CourseWebDto>> responseEntity = serviceAppRestClient.exchange(urlReadCourses, HttpMethod.POST, null, new ParameterizedTypeReference<>() {
        });
        List<CourseWebDto> courseWebDtoList = responseEntity.getBody() != null ?
                responseEntity.getBody().stream().toList() : Collections.emptyList();
        return courseWebDtoList.stream()
                .map(courseMapper::toEntityFromWebDto)
                .toList();
    }

    public List<Course> filterByCategoryName(String category) {
        String urlReadALLCourse = String.format("%s%s%s", ROOT_COURSE, METHOD_FILTER_CATEGORY_COURSES, category);
        ResponseEntity<List<CourseWebDto>> responseEntity = serviceAppRestClient.exchange(urlReadALLCourse, HttpMethod.POST, null, new ParameterizedTypeReference<>() {});
        List<CourseWebDto> courseWebDtoList = responseEntity.getBody() != null ?
                responseEntity.getBody().stream().toList() : Collections.emptyList();
        return courseWebDtoList.stream()
                .map(courseMapper::toEntityFromWebDto)
                .toList();
    }

}
