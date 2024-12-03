package by.ita.je.services;

import by.ita.je.models.Teacher;
import by.ita.je.repository.TeacherCrudRepository;
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

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeacherServiceTest extends TestUtils {

    @Mock
    private TeacherCrudRepository teacherCrudRepository;
    @InjectMocks
    private TeacherService teacherService;

    @Test
    void findByNumber_then_return_object() {
        Teacher testTeacher = buildTeacher(5, "John", "Adams", "John", "654", "teacher");

        when(teacherCrudRepository.findById(
                eq(testTeacher.getNumber())
        )).thenReturn(Optional.of(testTeacher));

        Teacher actual = teacherService.findByNumber(testTeacher.getNumber());

        assertEquals(actual, testTeacher);

        Mockito.verify(
                teacherCrudRepository, new Times(1)).findById(
                eq(testTeacher.getNumber())
        );
    }

    @Test
    void findByNumber_then_throw_NoSuchElementException() {
        Random random = new Random();
        Integer number = random.nextInt(Integer.MAX_VALUE);

        when(teacherCrudRepository.findById(
                eq(number)
        )).thenThrow(new NoSuchElementException());

        Assertions.assertThrows(NoSuchElementException.class, () -> teacherService.findByNumber(number));

        Mockito.verify(
                teacherCrudRepository, new Times(1)).findById(
                eq(number)
        );
    }

    @Test
    void readAll_then_return() {
        Teacher testTeacher1 = buildTeacher(5, "John", "Adams", "John", "654", "teacher");
        Teacher testTeacher2 = buildTeacher(6, "Eva", "Smith", "Eva", "321", "teacher");

        List<Teacher> testList = new ArrayList<>(Arrays.asList(testTeacher1, testTeacher2));

        when(teacherCrudRepository.findAll())
                .thenReturn(testList);

        List<Teacher> actualList = teacherService.readAll();

        assertEquals(actualList, testList);

        Mockito.verify(
                teacherCrudRepository, new Times(1)).findAll();
    }

    @Test
    void readAll_then_throws_DataAccessException() {

        when(teacherCrudRepository.findAll())
                .thenThrow(new DataAccessException("") {
                });

        assertThrows(DataAccessException.class, () -> teacherService.readAll());

        Mockito.verify(teacherCrudRepository, new Times(1)).findAll();
    }

    @Test
    void insert() {
        Teacher testTeacher = buildTeacher(5, "John", "Adams", "John", "654", "teacher");

        when(teacherCrudRepository.save(
                eq(testTeacher)
        )).thenReturn(testTeacher);

        Teacher actual = teacherService.insert(testTeacher);

        assertEquals(actual, testTeacher);

        Mockito.verify(
                teacherCrudRepository, new Times(1)).save(
                eq(testTeacher)
        );
    }

    @Test
    void update() {
        Teacher updateTeacher = buildTeacher(5, "John", "Adams", "John", "654", "teacher");

        when(teacherCrudRepository.findById(
                eq(updateTeacher.getNumber())
        )).thenReturn(Optional.of(updateTeacher));

        when(teacherCrudRepository.save(
                eq(updateTeacher)
        )).thenReturn(updateTeacher);

        Teacher actual = teacherService.update(updateTeacher);

        assertEquals(actual, updateTeacher);

        Mockito.verify(
                teacherCrudRepository, new Times(1)).findById(
                eq(updateTeacher.getNumber())
        );
        Mockito.verify(
                teacherCrudRepository, new Times(1)).save(
                eq(updateTeacher)
        );
    }

    @Test
    void update_then_throw_NoSuchElementException() {
        Random random = new Random();
        Integer number = random.nextInt(Integer.MAX_VALUE);

        Teacher updateTeacher = buildTeacher(5, "John", "Adams", "John", "654", "teacher");

        when(teacherCrudRepository.findById(
                eq(number)
        )).thenThrow(new NoSuchElementException());

        Assertions.assertThrows(NoSuchElementException.class, () -> teacherService.findByNumber(number));

        Mockito.verify(
                teacherCrudRepository, new Times(1)).findById(
                eq(number)
        );
        Mockito.verify(
                teacherCrudRepository, new Times(0)).save(
                eq(updateTeacher)
        );
    }

    @Test
    void delete_then_return() {
        Teacher deleteTeacher = buildTeacher(5, "John", "Adams", "John", "654", "teacher");

        when(teacherCrudRepository.findById(
                eq(deleteTeacher.getNumber())
        )).thenReturn(Optional.of(deleteTeacher));
        doNothing().when(teacherCrudRepository).deleteById(deleteTeacher.getNumber());

        Teacher actual = teacherService.delete(deleteTeacher.getNumber());

        assertEquals(actual, deleteTeacher);

        Mockito.verify(
                teacherCrudRepository, new Times(1)).findById(
                eq(deleteTeacher.getNumber())
        );
        Mockito.verify(
                teacherCrudRepository, new Times(1)).deleteById(
                eq(deleteTeacher.getNumber())
        );
    }

    @Test
    void delete_then_throw_NoSuchElementException() {
        Random random = new Random();
        Integer number = random.nextInt(Integer.MAX_VALUE);

        Teacher deleteTeacher = buildTeacher(5, "John", "Adams", "John", "654", "teacher");

        when(teacherCrudRepository.findById(
                eq(number)
        )).thenThrow(new NoSuchElementException());

        Assertions.assertThrows(NoSuchElementException.class, () -> teacherService.delete(number));

        Mockito.verify(
                teacherCrudRepository, new Times(1)).findById(
                eq(number)
        );
        Mockito.verify(
                teacherCrudRepository, new Times(0)).save(
                eq(deleteTeacher)
        );
    }

    @Test
    void deleteAll_then_return_emptyList() {
        doNothing().when(teacherCrudRepository).deleteAll();

        teacherService.deleteAll();

        List<Teacher> actualList = teacherService.readAll();
        assertEquals(actualList, Collections.emptyList());

        verify(teacherCrudRepository, new Times(1)).deleteAll();
    }
}