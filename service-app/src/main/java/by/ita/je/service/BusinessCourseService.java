package by.ita.je.service;

import by.ita.je.dto.to_data_base.CourseDto;
import by.ita.je.dto.to_data_base.StudentDto;
import by.ita.je.dto.to_web.TeacherWebDto;
import by.ita.je.mappers.CourseMapper;
import by.ita.je.mappers.TeacherMapper;
import by.ita.je.models.Course;
import by.ita.je.models.Recruiting;
import by.ita.je.models.Registration;
import by.ita.je.models.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BusinessCourseService {
    public static final String ROOT_COURSE = "/database/course";
    public static final String ROOT_STUDENT = "/database/student";
    public static final String ROOT_TEACHER = "/database/teacher";
    public static final String ROOT_RECRUITING = "/database/recruiting";

    public static final String METOD_READ = "/read/";
    public static final String METOD_READ_ALL = "/read/all";
    public static final String METOD_CREATE = "/create";
    public static final String METOD_UPDATE = "/update";
    public static final String METOD_DELETE = "/delete/";
    public static final String METOD_FILTER_CATEGORY = "/filter/category?numberCategory=";
    public static final String METOD_FILTER_CATEGORY_NAME = "/filter/category/name?category=";
    public static final String METOD_FILTER_PRICE = "/filter/price?price=";
    public static final String METOD_FILTER_DURATION = "/filter/duration?duration=";
    public static final String METOD_FILTER_TEACHER_SURNAME = "/filter/surname?surname=";

    private final RestTemplate serviceAppRestClient;
    private final CourseMapper courseMapper;
    private final TeacherMapper teacherMapper;

    public List<Course> readAll() {
        String urlReadALLCourse = String.format("%s%s", ROOT_COURSE, METOD_READ_ALL);

        ResponseEntity<List<CourseDto>> responseEntity = serviceAppRestClient.exchange(urlReadALLCourse, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
        List<CourseDto> courseDtoList = responseEntity.getBody() != null ?
                responseEntity.getBody().stream().toList() : Collections.emptyList();
        return courseDtoList.stream()
                .map(courseMapper::toEntity)
                .toList();
    }

    public Course findCourseAndAddStudent(Registration registration) {
        String surname = registration.getSurname();
        String urlReadALLStudent = String.format("%s%s", ROOT_STUDENT, METOD_READ_ALL);

        ResponseEntity<List<StudentDto>> responseEntity = serviceAppRestClient.exchange(urlReadALLStudent, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
        List<StudentDto> studentDtoList = responseEntity.getBody() != null ?
                responseEntity.getBody().stream().toList() : Collections.emptyList();


        Integer numberCourse = registration.getNumber();
        String urlReadCourse = String.format("%s%s%d", ROOT_COURSE, METOD_READ, numberCourse);
        CourseDto courseDto = serviceAppRestClient.getForObject(urlReadCourse, CourseDto.class);

        boolean studentFound = false;
        for (StudentDto studentDto : studentDtoList) {
            if (Objects.equals(studentDto.getSurname(), surname)) {
                studentFound = true;
                if (studentDto.getBalance().compareTo(courseDto.getPrice()) >= 0) {
                    studentDto.setBalance(studentDto.getBalance().subtract(courseDto.getPrice()));
                    courseDto.getStudents().add(studentDto);
                } else throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "Insufficient funds");
            }
        }
        if (!studentFound) {
            throw new NoSuchElementException("Please check your data or register");
        }
        Course course = courseMapper.toEntity(courseDto);

        String urlUdate = String.format("%s%s", ROOT_COURSE, METOD_UPDATE);
        serviceAppRestClient.put(urlUdate, courseDto, CourseDto.class);
        return course;
    }

    public Course create(Course course) {
        String urlCreateCourse = String.format("%s%s", ROOT_COURSE, METOD_CREATE);
        CourseDto courseDto = courseMapper.toDTO(course);
        CourseDto courseDtoCreate = serviceAppRestClient.postForObject(urlCreateCourse, courseDto, CourseDto.class);
        return courseMapper.toEntity(courseDtoCreate);
    }

    public List<Course> coursesByCategory(Integer numberCategory) {
        String urlFilterByCategory = String.format("%s%s%d", ROOT_COURSE, METOD_FILTER_CATEGORY, numberCategory);

        ResponseEntity<List<CourseDto>> responseEntity = serviceAppRestClient.exchange(urlFilterByCategory, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });

        List<CourseDto> courseDtoList = responseEntity.getBody() != null ?
                responseEntity.getBody().stream().toList() : Collections.emptyList();
        return courseDtoList.stream()
                .map(courseMapper::toEntity)
                .toList();
    }

    public List<Course> coursesByTeacher(String teacherSurname) {

        String urlReadAllCourse = String.format("%s%s", ROOT_COURSE, METOD_READ_ALL);

        ResponseEntity<List<CourseDto>> responseEntity = serviceAppRestClient.exchange(urlReadAllCourse, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
        List<CourseDto> courseDtoList = responseEntity.getBody() != null ?
                responseEntity.getBody().stream().toList() : Collections.emptyList();

        List<CourseDto> listByTeacher = new ArrayList<>();
        for (CourseDto courseDto : courseDtoList) {
            if (Objects.equals(courseDto.getTeacher().getSurname(), teacherSurname)) {
                listByTeacher.add(courseDto);
            }
        }
        return listByTeacher.stream()
                .map(courseMapper::toEntity)
                .toList();
    }

    public List<Course> coursesByLocation(String location) {

        String urlReadAllCourse = String.format("%s%s", ROOT_COURSE, METOD_READ_ALL);

        ResponseEntity<List<CourseDto>> responseEntity = serviceAppRestClient.exchange(urlReadAllCourse, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
        List<CourseDto> courseDtoList = responseEntity.getBody() != null ?
                responseEntity.getBody().stream().toList() : Collections.emptyList();

        List<CourseDto> listByLocation = new ArrayList<>();
        for (CourseDto courseDto : courseDtoList) {
            if (Objects.equals(courseDto.getLocation(), location)) {
                listByLocation.add(courseDto);
            }
        }
        return listByLocation.stream()
                .map(courseMapper::toEntity)
                .toList();
    }

    public List<Course> coursesByPrice(Integer price) {
        String urlFilterByPrice = String.format("%s%s%d", ROOT_COURSE, METOD_FILTER_PRICE, price);

        ResponseEntity<List<CourseDto>> responseEntity = serviceAppRestClient.exchange(urlFilterByPrice, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });

        List<CourseDto> courseDtoList = responseEntity.getBody() != null ?
                responseEntity.getBody().stream().toList() : Collections.emptyList();
        return courseDtoList.stream()
                .map(courseMapper::toEntity)
                .toList();
    }

    public List<Course> coursesByDuration(Integer duration) {
        String urlFilterByDuration = String.format("%s%s%d", ROOT_COURSE, METOD_FILTER_DURATION, duration);

        ResponseEntity<List<CourseDto>> responseEntity = serviceAppRestClient.exchange(urlFilterByDuration, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });

        List<CourseDto> courseDtoList = responseEntity.getBody() != null ?
                responseEntity.getBody().stream().toList() : Collections.emptyList();
        return courseDtoList.stream()
                .map(courseMapper::toEntity)
                .toList();
    }

    public Course deleteCourse(Integer number) {

        String urlReadCourse = String.format("%s%s%d", ROOT_COURSE, METOD_READ, number);
        String urlDeleteCourse = String.format("%s%s%d", ROOT_COURSE, METOD_DELETE, number);
        CourseDto courseDto = serviceAppRestClient.getForObject(urlReadCourse, CourseDto.class);

        if (courseDto != null) {
            serviceAppRestClient.delete(urlDeleteCourse);
        }
        return courseMapper.toEntity(courseDto);
    }

    public Teacher teacherBySurname(String surname) {
        String urlTeacherBySurname = String.format("%s%s%s", ROOT_TEACHER, METOD_FILTER_TEACHER_SURNAME, surname);

        ResponseEntity<List<TeacherWebDto>> responseEntity = serviceAppRestClient.exchange(urlTeacherBySurname, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
        List<TeacherWebDto> teacherWebDtoList = responseEntity.getBody() != null ?
                responseEntity.getBody().stream().toList() : Collections.emptyList();
        return teacherMapper.toEntityFromWebDto(teacherWebDtoList.get(0));
    }

    public Recruiting createRecruiting(Recruiting recruiting) {
        String urlCreateRecruiting = String.format("%s%s", ROOT_RECRUITING, METOD_CREATE);
        return serviceAppRestClient.postForObject(urlCreateRecruiting, recruiting, Recruiting.class);

    }

    public List<Course> coursesByCategoryName(String category) {
        String urlFilterByCategoryName = String.format("%s%s%s", ROOT_COURSE, METOD_FILTER_CATEGORY_NAME, category);

        ResponseEntity<List<CourseDto>> responseEntity = serviceAppRestClient.exchange(urlFilterByCategoryName, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});

        List<CourseDto> courseDtoList = responseEntity.getBody() != null ?
                responseEntity.getBody().stream().toList() : Collections.emptyList();
        return courseDtoList.stream()
                .map(courseMapper::toEntity)
                .toList();
    }
}
