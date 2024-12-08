package by.ita.je.service;

import by.ita.je.dto.CourseWebDto;
import by.ita.je.mappers.CourseMapper;
import by.ita.je.models.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CourseWebService {
    private final RestTemplate serviceAppRestClient;
    private final CourseMapper courseMapper;

    public static final String ROOT_COURSE = "/business/course";
    public static final String ROOT_STUDENT = "/business/student";

    public static final String METOD_CREATE = "/create";

    public Course createCourse(Course course) {
        CourseWebDto courseWebDto = courseMapper.toWebDTO(course);
        String urlCreate = String.format("%s%s", ROOT_COURSE, METOD_CREATE);
        return courseMapper.toEntityFromWebDto(serviceAppRestClient.postForObject(urlCreate, courseWebDto, CourseWebDto.class));
    }
}
