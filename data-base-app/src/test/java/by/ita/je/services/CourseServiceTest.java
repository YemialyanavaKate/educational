package by.ita.je.services;

import by.ita.je.models.Course;
import by.ita.je.repository.CourseCrudRepository;
import by.ita.je.services.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.Times;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest extends TestUtils {

    @Mock
    private CourseCrudRepository courseCrudRepository;
    @InjectMocks
    private CourseService courseService;

    @Test
    void findByNumber_then_return_object() {
        Course testCourse = buildCourse(1, "AI","Minsk", BigDecimal.valueOf(3000), 4, ZonedDateTime.parse("2024-01-10T10:00:00+02"), ZonedDateTime.parse("2024-05-10T10:00:00+02"));

        when(courseCrudRepository.findById(
                eq(testCourse.getNumber())
        )).thenReturn(Optional.of(testCourse));

        Course actual = courseService.findByNumber(testCourse.getNumber());

        assertEquals(actual, testCourse);

        Mockito.verify(
                courseCrudRepository, new Times(1)).findById(
                        eq(testCourse.getNumber())
        );
    }

    @Test
    void findByNumber_then_throw_NoSuchElementException() {
        Random random = new Random();
        Integer number = random.nextInt(Integer.MAX_VALUE);

        when(courseCrudRepository.findById(
                eq(number)
        )).thenThrow(new NoSuchElementException());

        Assertions.assertThrows(NoSuchElementException.class,() -> courseService.findByNumber(number));

        Mockito.verify(
                courseCrudRepository, new Times(1)).findById(
                eq(number)
        );
    }

    @Test
    void readAll_then_return() {
        Course testCourse1 = buildCourse(1, "AI","Minsk", BigDecimal.valueOf(3000), 4, ZonedDateTime.parse("2024-01-10T10:00:00+02"), ZonedDateTime.parse("2024-05-10T10:00:00+02"));
        Course testCourse2 = buildCourse(2, "JAVA","Minsk", BigDecimal.valueOf(5000), 4, ZonedDateTime.parse("2024-01-10T10:00:00+02"), ZonedDateTime.parse("2024-05-10T10:00:00+02"));

        List<Course> testList = new ArrayList<>(Arrays.asList(testCourse1, testCourse2));

        when(courseCrudRepository.findAll())
                .thenReturn(testList);

        List<Course> actualList = courseService.readAll();

        assertEquals(actualList, testList);

        Mockito.verify(
                courseCrudRepository, new Times(1)).findAll();
    }

    @Test
    void readAll_then_throws_DataAccessException() {

        when(courseCrudRepository.findAll())
                .thenThrow(new DataAccessException("") {});

        assertThrows(DataAccessException.class, () -> courseService.readAll());

        Mockito.verify(courseCrudRepository, new Times(1)).findAll();
    }

    @Test
    void insert() {
        Course testCourse = buildCourse(6, "AI","Minsk", BigDecimal.valueOf(3000), 4, ZonedDateTime.parse("2024-01-10T10:00:00+02"), ZonedDateTime.parse("2024-05-10T10:00:00+02"));

        when(courseCrudRepository.save(
                eq(testCourse)
        )).thenReturn(testCourse);

        Course actual = courseService.insert(testCourse);

        assertEquals(actual, testCourse);

        Mockito.verify(
                courseCrudRepository, new Times(1)).save(
                eq(testCourse)
        );
    }

    @Test
    void update() {
        Course updateCourse = buildCourse(1, "AI","Minsk", BigDecimal.valueOf(3000), 4, ZonedDateTime.parse("2024-01-10T10:00:00+02"), ZonedDateTime.parse("2024-05-10T10:00:00+02"));

        when(courseCrudRepository.findById(
                eq(updateCourse.getNumber())
        )).thenReturn(Optional.of(updateCourse));

        when(courseCrudRepository.save(
                eq(updateCourse)
        )).thenReturn(updateCourse);

        Course actual = courseService.update(updateCourse);

        assertEquals(actual, updateCourse);

        Mockito.verify(
                courseCrudRepository, new Times(1)).findById(
                eq(updateCourse.getNumber())
        );
        Mockito.verify(
                courseCrudRepository, new Times(1)).save(
                eq(updateCourse)
        );
    }
    @Test
    void update_then_throw_NoSuchElementException() {
        Random random = new Random();
        Integer number = random.nextInt(Integer.MAX_VALUE);

        Course updateCourse = buildCourse(number, "AI","Minsk", BigDecimal.valueOf(3000), 4, ZonedDateTime.parse("2024-01-10T10:00:00+02"), ZonedDateTime.parse("2024-05-10T10:00:00+02"));

        when(courseCrudRepository.findById(
                eq(number)
        )).thenThrow(new NoSuchElementException());

        Assertions.assertThrows(NoSuchElementException.class,() -> courseService.findByNumber(number));

        Mockito.verify(
                courseCrudRepository, new Times(1)).findById(
                eq(number)
        );
        Mockito.verify(
                courseCrudRepository, new Times(0)).save(
                eq(updateCourse)
        );
    }

    @Test
    void delete_then_return() {
        Course deleteCourse = buildCourse(1, "AI","Minsk", BigDecimal.valueOf(3000), 4, ZonedDateTime.parse("2024-01-10T10:00:00+02"), ZonedDateTime.parse("2024-05-10T10:00:00+02"));

        when(courseCrudRepository.findById(
                eq(deleteCourse.getNumber())
        )).thenReturn(Optional.of(deleteCourse));
        doNothing().when(courseCrudRepository).deleteById(deleteCourse.getNumber());

        Course actual = courseService.delete(deleteCourse.getNumber());

        assertEquals(actual, deleteCourse);

        Mockito.verify(
                courseCrudRepository, new Times(1)).findById(
                eq(deleteCourse.getNumber())
        );
        Mockito.verify(
                courseCrudRepository, new Times(1)).deleteById(
                eq(deleteCourse.getNumber())
        );
    }

    @Test
    void delete_then_throw_NoSuchElementException() {
        Random random = new Random();
        Integer number = random.nextInt(Integer.MAX_VALUE);

        Course deleteCourse = buildCourse(number, "AI","Minsk", BigDecimal.valueOf(3000), 4, ZonedDateTime.parse("2024-01-10T10:00:00+02"), ZonedDateTime.parse("2024-05-10T10:00:00+02"));

        when(courseCrudRepository.findById(
                eq(number)
        )).thenThrow(new NoSuchElementException());

        Assertions.assertThrows(NoSuchElementException.class,() -> courseService.delete(number));

        Mockito.verify(
                courseCrudRepository, new Times(1)).findById(
                eq(number)
        );
        Mockito.verify(
                courseCrudRepository, new Times(0)).save(
                eq(deleteCourse)
        );
    }

    @Test
    void deleteAll_then_return_emptyList() {
        doNothing().when(courseCrudRepository).deleteAll();

        courseService.deleteAll();

        List<Course> actualList = courseService.readAll();
        assertEquals(actualList, Collections.emptyList());

        verify(courseCrudRepository, new Times(1)).deleteAll();
    }
}