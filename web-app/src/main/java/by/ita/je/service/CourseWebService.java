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

    public static final String METOD_CREATE = "/create";
    public static final String METOD_READ_ALL = "/read/all";
    public static final String METOD_FILTER_BY_TEACHER = "/create/by/teacher?teacherSurname=";
    public static final String METOD_READ_TEACHER = "/read/teacher?surname=";
    public static final String METOD_CREATE_RECRUITING = "/recruiting/create";
    public static final String METOD_DELETE = "/delete/";

    public Course createCourse(Course course) {
        String surname = course.getTeacher().getSurname();
        String urlReadTeachers = String.format("%s%s%s", ROOT_COURSE, METOD_READ_TEACHER, surname);

        CourseWebDto courseWebDto = courseMapper.toWebDTO(course);
        //courseWebDto.setTeacher(teacherWebDto);
        String urlCreate = String.format("%s%s", ROOT_COURSE, METOD_CREATE);
        return courseMapper.toEntityFromWebDto(serviceAppRestClient.postForObject(urlCreate, courseWebDto, CourseWebDto.class));
    }

    public List<Course> readeAllCourse() {
        String urlReadALLCourse = String.format("%s%s", ROOT_COURSE, METOD_READ_ALL);
        ResponseEntity<List<CourseWebDto>> responseEntity = serviceAppRestClient.exchange(urlReadALLCourse, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
        List<CourseWebDto> courseWebDtoList = responseEntity.getBody() != null ?
                responseEntity.getBody().stream().toList() : Collections.emptyList();
        return courseWebDtoList.stream()
                .map(courseMapper::toEntityFromWebDto)
                .toList();
    }

    public List<Course> filterByTeacher(String surname) {
        String urlReadALLCourse = String.format("%s%s%s", ROOT_COURSE, METOD_FILTER_BY_TEACHER, surname);
        ResponseEntity<List<CourseWebDto>> responseEntity = serviceAppRestClient.exchange(urlReadALLCourse, HttpMethod.POST, null, new ParameterizedTypeReference<>() {
        });
        List<CourseWebDto> courseWebDtoList = responseEntity.getBody() != null ?
                responseEntity.getBody().stream().toList() : Collections.emptyList();
        return courseWebDtoList.stream()
                .map(courseMapper::toEntityFromWebDto)
                .toList();
    }

    public Recruiting createRecruiting(Recruiting recruiting) {
        String urlCreateRecruiting = String.format("%s%s", ROOT_COURSE, METOD_CREATE_RECRUITING);
        return serviceAppRestClient.postForObject(urlCreateRecruiting, recruiting, Recruiting.class);
    }

    public void deleteCourse(Integer number) {
        String urlDeleteCourse = String.format("%s%s%d", ROOT_COURSE, METOD_DELETE, number);
        serviceAppRestClient.delete(urlDeleteCourse);
    }
}
