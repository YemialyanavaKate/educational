package by.ita.je.service;

import by.ita.je.dto.to_data_base.CourseDto;
import by.ita.je.dto.to_data_base.TeacherDto;
import by.ita.je.mappers.*;
import by.ita.je.models.Course;
import by.ita.je.models.Teacher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.Times;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BusinessCourseServiceTest {
    @Mock
    private RestTemplate serviceAppRestClient;
    @Mock
    private CourseMapper courseMapper;
    @Mock
    private RegistrationMapper registrationMapper;
    @InjectMocks
    private BusinessCourseService businessCourseService;

    @Test
    void create() {
        String urlCreateCourse = "/database/course/create";
        Course testCourse = Course.builder()
                .name("ProjectManeger")
                .price(BigDecimal.valueOf(2000))
                .build();
        CourseDto testCourseDto = CourseDto.builder()
                .name("ProjectManeger")
                .price(BigDecimal.valueOf(2000))
                .build();

        when(courseMapper.toDTO(testCourse)).thenReturn(testCourseDto);
        when(courseMapper.toEntity(serviceAppRestClient.postForObject(
                eq(urlCreateCourse),
                eq(testCourseDto),
                eq(CourseDto.class)

        ))).thenReturn(testCourse);

        Course actual = businessCourseService.create(testCourse);

        assertEquals(actual, testCourse);

        verify(courseMapper, new Times(1)).toDTO(testCourse);

        verify(serviceAppRestClient, new Times(1)).postForObject(
                eq(urlCreateCourse),
                eq(testCourseDto),
                eq(CourseDto.class)
        );
    }
    @Test
    void readAll() {

        String urlReadAllCourse = "/database/course/read/all";
        Course testCourse = Course.builder()
                .name("ProjectManeger")
                .price(BigDecimal.valueOf(2000))
                .build();
        Course testCourse2 = Course.builder()
                .name("ProjectManeger2")
                .price(BigDecimal.valueOf(3000))
                .build();
        CourseDto testCourseDto1 = CourseDto.builder()
                .name("ProjectManeger")
                .price(BigDecimal.valueOf(2000))
                .build();
        CourseDto testCourseDto2 = CourseDto.builder()
                .name("ProjectManeger2")
                .price(BigDecimal.valueOf(3000))
                .build();

        List<Course> courseList = Arrays.asList(testCourse,  testCourse2);
        List<CourseDto> courseDtoList = Arrays.asList(testCourseDto1, testCourseDto2);

        ResponseEntity<List<CourseDto>> responseEntity = ResponseEntity.ok().body(courseDtoList);

        when(serviceAppRestClient.exchange(
                eq(urlReadAllCourse),
                eq(HttpMethod.GET),
                eq(null),
                eq(new ParameterizedTypeReference<List<CourseDto>>() {})
        )).thenReturn(responseEntity);
        when(courseMapper.toEntity(testCourseDto1)).thenReturn(testCourse);
        when(courseMapper.toEntity(testCourseDto2)).thenReturn(testCourse2);

        List<Course> actualList = businessCourseService.readAll();

        assertEquals(actualList.get(0).getNumber(), courseList.get(0).getNumber());
        assertEquals(actualList.get(0).getName(), courseList.get(0).getName());
        assertEquals(actualList.get(1).getNumber(), courseList.get(1).getNumber());
        assertEquals(actualList.get(1).getName(), courseList.get(1).getName());
        verify(serviceAppRestClient, new Times(1)).exchange(
                eq(urlReadAllCourse),
                eq(HttpMethod.GET),
                eq(null),
                eq(new ParameterizedTypeReference<List<CourseDto>>() {})
        );

        verify(courseMapper, new Times(1)).toEntity(testCourseDto1);
        verify(courseMapper, new Times(1)).toEntity(testCourseDto2);
    }

    @Test
    void coursesByCategory() {
        String urlReadAllCourse = "/database/course/filter/category?numberCategory=2";
        Course testCourse = Course.builder()
                .number(4)
                .name("Manager_core")
                .location("Minsk")
                .price(BigDecimal.valueOf(1000))
                .duration(3)
                .build();
        CourseDto testCourseDto = CourseDto.builder()
                .number(4)
                .name("Manager_core")
                .location("Minsk")
                .price(BigDecimal.valueOf(1000))
                .duration(3)
                .build();
        List<Course> courseList = Collections.singletonList(testCourse);
        List<CourseDto> courseDtoList = Collections.singletonList(testCourseDto);

        ResponseEntity<List<CourseDto>> responseEntity = ResponseEntity.ok().body(courseDtoList);

        when(serviceAppRestClient.exchange(
                eq(urlReadAllCourse),
                eq(HttpMethod.GET),
                eq(null),
                eq(new ParameterizedTypeReference<List<CourseDto>>() {})
        )).thenReturn(responseEntity);

        when(courseMapper.toEntity(testCourseDto)).thenReturn(testCourse);

        List<Course> actualList = businessCourseService.coursesByCategory(2);

        assertEquals(actualList.get(0).getNumber(), courseList.get(0).getNumber());
        assertEquals(actualList.get(0).getName(), courseList.get(0).getName());

        verify(serviceAppRestClient, new Times(1)).exchange(
                eq(urlReadAllCourse),
                eq(HttpMethod.GET),
                eq(null),
                eq(new ParameterizedTypeReference<List<CourseDto>>() {})
        );

        verify(courseMapper, new Times(1)).toEntity(testCourseDto);

    }

    @Test
    void coursesByTeacher() {
        String urlReadAllCourse = "/database/course/read/all";
        Teacher teacher = Teacher.builder()
                .number(2)
                .name("Jon")
                .surname("Adams")
                .role("teacher")
                .build();
        TeacherDto teacherDto = TeacherDto.builder()
                .number(2)
                .name("Jon")
                .surname("Adams")
                .role("teacher")
                .build();

        Course testCourse1 = Course.builder()
                .number(4)
                .name("Manager_core")
                .location("Minsk")
                .price(BigDecimal.valueOf(1000))
                .duration(3)
                .teacher(teacher)
                .build();
        CourseDto testCourse1Dto = CourseDto.builder()
                .number(4)
                .name("Manager_core")
                .location("Minsk")
                .price(BigDecimal.valueOf(1000))
                .duration(3)
                .teacher(teacherDto)
                .build();
        Course testCourse2 = Course.builder()
                .number(2)
                .name("JAVA_ENTERPRISE")
                .location("Brest")
                .price(BigDecimal.valueOf(3000))
                .duration(5)
                .teacher(teacher)
                .build();
        CourseDto testCourse2Dto = CourseDto.builder()
                .number(2)
                .name("JAVA_ENTERPRISE")
                .location("Brest")
                .price(BigDecimal.valueOf(3000))
                .duration(5)
                .teacher(teacherDto)
                .build();
        List<Course> courseList = Arrays.asList(testCourse1,testCourse2);
        List<CourseDto> courseDtoList = Arrays.asList(testCourse1Dto, testCourse2Dto);

        ResponseEntity<List<CourseDto>> responseEntity = ResponseEntity.ok().body(courseDtoList);

        when(serviceAppRestClient.exchange(
                eq(urlReadAllCourse),
                eq(HttpMethod.GET),
                eq(null),
                eq(new ParameterizedTypeReference<List<CourseDto>>() {})
        )).thenReturn(responseEntity);

        when(courseMapper.toEntity(testCourse1Dto)).thenReturn(testCourse1);
        when(courseMapper.toEntity(testCourse2Dto)).thenReturn(testCourse2);

        String teacherSurname = "Adams";
        List<Course> actualList = businessCourseService.coursesByTeacher(teacherSurname);

        assertEquals(actualList.get(0).getNumber(), courseList.get(0).getNumber());
        assertEquals(actualList.get(0).getName(), courseList.get(0).getName());
        assertEquals(actualList.get(1).getNumber(), courseList.get(1).getNumber());
        assertEquals(actualList.get(1).getName(), courseList.get(1).getName());
        assertEquals(teacherSurname, actualList.get(0).getTeacher().getSurname());
        assertEquals(teacherSurname, actualList.get(1).getTeacher().getSurname());

        verify(serviceAppRestClient, new Times(1)).exchange(
                eq(urlReadAllCourse),
                eq(HttpMethod.GET),
                eq(null),
                eq(new ParameterizedTypeReference<List<CourseDto>>() {})
        );

        verify(courseMapper, new Times(1)).toEntity(testCourse1Dto);
        verify(courseMapper, new Times(1)).toEntity(testCourse2Dto);

    }

    @Test
    void coursesByLocation() {
        String urlReadAllCourse = "/database/course/read/all";

        Course testCourse = Course.builder()
                .number(2)
                .name("JAVA_ENTERPRISE")
                .location("Brest")
                .price(BigDecimal.valueOf(3000))
                .duration(5)
                .build();
        CourseDto testCourseDto = CourseDto.builder()
                .number(2)
                .name("JAVA_ENTERPRISE")
                .location("Brest")
                .price(BigDecimal.valueOf(3000))
                .duration(5)
                .build();
        List<Course> courseList = Collections.singletonList(testCourse);
        List<CourseDto> courseDtoList = Collections.singletonList(testCourseDto);

        ResponseEntity<List<CourseDto>> responseEntity = ResponseEntity.ok().body(courseDtoList);

        when(serviceAppRestClient.exchange(
                eq(urlReadAllCourse),
                eq(HttpMethod.GET),
                eq(null),
                eq(new ParameterizedTypeReference<List<CourseDto>>() {})
        )).thenReturn(responseEntity);

        when(courseMapper.toEntity(testCourseDto)).thenReturn(testCourse);
        String location = "Brest";
        List<Course> actualList = businessCourseService.coursesByLocation(location);

        assertEquals(actualList.get(0).getNumber(), courseList.get(0).getNumber());
        assertEquals(actualList.get(0).getName(), courseList.get(0).getName());
        assertEquals(location, actualList.get(0).getLocation());

        verify(serviceAppRestClient, new Times(1)).exchange(
                eq(urlReadAllCourse),
                eq(HttpMethod.GET),
                eq(null),
                eq(new ParameterizedTypeReference<List<CourseDto>>() {})
        );

        verify(courseMapper, new Times(1)).toEntity(testCourseDto);

    }

    @Test
    void coursesByPrice() {
        String urlFilterByPrice = "/database/course/filter/price?price=1000";
        Course testCourse = Course.builder()
                .number(4)
                .name("Manager_core")
                .location("Minsk")
                .price(BigDecimal.valueOf(1000))
                .duration(3)
                .build();
        CourseDto testCourseDto = CourseDto.builder()
                .number(4)
                .name("Manager_core")
                .location("Minsk")
                .price(BigDecimal.valueOf(1000))
                .duration(3)
                .build();
        List<Course> courseList = Collections.singletonList(testCourse);
        List<CourseDto> courseDtoList = Collections.singletonList(testCourseDto);

        ResponseEntity<List<CourseDto>> responseEntity = ResponseEntity.ok().body(courseDtoList);

        when(serviceAppRestClient.exchange(
                eq(urlFilterByPrice),
                eq(HttpMethod.GET),
                eq(null),
                eq(new ParameterizedTypeReference<List<CourseDto>>() {})
        )).thenReturn(responseEntity);

        when(courseMapper.toEntity(testCourseDto)).thenReturn(testCourse);

        List<Course> actualList = businessCourseService.coursesByPrice(1000);

        assertEquals(actualList.get(0).getNumber(), courseList.get(0).getNumber());
        assertEquals(actualList.get(0).getName(), courseList.get(0).getName());

        verify(serviceAppRestClient, new Times(1)).exchange(
                eq(urlFilterByPrice),
                eq(HttpMethod.GET),
                eq(null),
                eq(new ParameterizedTypeReference<List<CourseDto>>() {})
        );

        verify(courseMapper, new Times(1)).toEntity(testCourseDto);
    }

    @Test
    void coursesByDuration() {
        String urlFilterByDuration = "/database/course/filter/duration?duration=3";
        Course testCourse = Course.builder()
                .number(4)
                .name("Manager_core")
                .location("Minsk")
                .price(BigDecimal.valueOf(1000))
                .duration(3)
                .build();
        CourseDto testCourseDto = CourseDto.builder()
                .number(4)
                .name("Manager_core")
                .location("Minsk")
                .price(BigDecimal.valueOf(1000))
                .duration(3)
                .build();
        List<Course> courseList = Collections.singletonList(testCourse);
        List<CourseDto> courseDtoList = Collections.singletonList(testCourseDto);

        ResponseEntity<List<CourseDto>> responseEntity = ResponseEntity.ok().body(courseDtoList);

        when(serviceAppRestClient.exchange(
                eq(urlFilterByDuration),
                eq(HttpMethod.GET),
                eq(null),
                eq(new ParameterizedTypeReference<List<CourseDto>>() {})
        )).thenReturn(responseEntity);

        when(courseMapper.toEntity(testCourseDto)).thenReturn(testCourse);

        List<Course> actualList = businessCourseService.coursesByDuration(3);

        assertEquals(actualList.get(0).getNumber(), courseList.get(0).getNumber());
        assertEquals(actualList.get(0).getName(), courseList.get(0).getName());

        verify(serviceAppRestClient, new Times(1)).exchange(
                eq(urlFilterByDuration),
                eq(HttpMethod.GET),
                eq(null),
                eq(new ParameterizedTypeReference<List<CourseDto>>() {})
        );

        verify(courseMapper, new Times(1)).toEntity(testCourseDto);
    }

    @Test
    void deleteCourse() {
        String urlFilterByDuration = "/database/course/filter/duration?duration=3";
        Course testCourse = Course.builder()
                .number(4)
                .name("Manager_core")
                .location("Minsk")
                .price(BigDecimal.valueOf(1000))
                .duration(3)
                .build();
        CourseDto testCourseDto = CourseDto.builder()
                .number(4)
                .name("Manager_core")
                .location("Minsk")
                .price(BigDecimal.valueOf(1000))
                .duration(3)
                .build();
        List<Course> courseList = Collections.singletonList(testCourse);
        List<CourseDto> courseDtoList = Collections.singletonList(testCourseDto);

        ResponseEntity<List<CourseDto>> responseEntity = ResponseEntity.ok().body(courseDtoList);

        when(serviceAppRestClient.exchange(
                eq(urlFilterByDuration),
                eq(HttpMethod.GET),
                eq(null),
                eq(new ParameterizedTypeReference<List<CourseDto>>() {})
        )).thenReturn(responseEntity);

        when(courseMapper.toEntity(testCourseDto)).thenReturn(testCourse);

        List<Course> actualList = businessCourseService.coursesByDuration(3);

        assertEquals(actualList.get(0).getNumber(), courseList.get(0).getNumber());
        assertEquals(actualList.get(0).getName(), courseList.get(0).getName());

        verify(serviceAppRestClient, new Times(1)).exchange(
                eq(urlFilterByDuration),
                eq(HttpMethod.GET),
                eq(null),
                eq(new ParameterizedTypeReference<List<CourseDto>>() {})
        );

        verify(courseMapper, new Times(1)).toEntity(testCourseDto);

    }
}