package by.ita.je.services;

import by.ita.je.models.Student;
import by.ita.je.repository.StudentCrudRepository;
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
class StudentServiceTest extends TestUtils {

    @Mock
    private StudentCrudRepository studentCrudRepository;
    @InjectMocks
    private StudentService studentService;

    @Test
    void findByNumber_then_return_object() {
        Student testStudent = buildStudent(5, "Roland", "Clark", "Roland", "10", "student");

        when(studentCrudRepository.findById(
                eq(testStudent.getNumber())
        )).thenReturn(Optional.of(testStudent));

        Student actual = studentService.findByNumber(testStudent.getNumber());

        assertEquals(actual, testStudent);

        Mockito.verify(
                studentCrudRepository, new Times(1)).findById(
                eq(testStudent.getNumber())
        );
    }

    @Test
    void findByNumber_then_throw_NoSuchElementException() {
        Random random = new Random();
        Integer number = random.nextInt(Integer.MAX_VALUE);

        when(studentCrudRepository.findById(
                eq(number)
        )).thenThrow(new NoSuchElementException());

        Assertions.assertThrows(NoSuchElementException.class, () -> studentService.findByNumber(number));

        Mockito.verify(
                studentCrudRepository, new Times(1)).findById(
                eq(number)
        );
    }

    @Test
    void readAll_then_return() {
        Student testStudent1 = buildStudent(5, "Roland", "Clark", "Roland", "10", "student");
        Student testStudent2 = buildStudent(6, "Tea", "Li", "Tea", "11", "student");

        List<Student> testList = new ArrayList<>(Arrays.asList(testStudent1, testStudent2));

        when(studentCrudRepository.findAll())
                .thenReturn(testList);

        List<Student> actualList = studentService.readAll();

        assertEquals(actualList, testList);

        Mockito.verify(
                studentCrudRepository, new Times(1)).findAll();
    }

    @Test
    void readAll_then_throws_DataAccessException() {

        when(studentCrudRepository.findAll())
                .thenThrow(new DataAccessException("") {
                });

        assertThrows(DataAccessException.class, () -> studentService.readAll());

        Mockito.verify(studentCrudRepository, new Times(1)).findAll();
    }

    @Test
    void insert() {
        Student testStudent = buildStudent(5, "Roland", "Clark", "Roland", "10", "student");

        when(studentCrudRepository.save(
                eq(testStudent)
        )).thenReturn(testStudent);

        Student actual = studentService.insert(testStudent);

        assertEquals(actual, testStudent);

        Mockito.verify(
                studentCrudRepository, new Times(1)).save(
                eq(testStudent)
        );
    }

    @Test
    void update() {
        Student updateStudent = buildStudent(5, "Roland", "Clark", "Roland", "10", "student");

        when(studentCrudRepository.findById(
                eq(updateStudent.getNumber())
        )).thenReturn(Optional.of(updateStudent));

        when(studentCrudRepository.save(
                eq(updateStudent)
        )).thenReturn(updateStudent);

        Student actual = studentService.update(updateStudent);

        assertEquals(actual, updateStudent);

        Mockito.verify(
                studentCrudRepository, new Times(1)).findById(
                eq(updateStudent.getNumber())
        );
        Mockito.verify(
                studentCrudRepository, new Times(1)).save(
                eq(updateStudent)
        );
    }

    @Test
    void update_then_throw_NoSuchElementException() {
        Random random = new Random();
        Integer number = random.nextInt(Integer.MAX_VALUE);

        Student updateStudent = buildStudent(5, "Roland", "Clark", "Roland", "10", "student");

        when(studentCrudRepository.findById(
                eq(number)
        )).thenThrow(new NoSuchElementException());

        Assertions.assertThrows(NoSuchElementException.class, () -> studentService.findByNumber(number));

        Mockito.verify(
                studentCrudRepository, new Times(1)).findById(
                eq(number)
        );
        Mockito.verify(
                studentCrudRepository, new Times(0)).save(
                eq(updateStudent)
        );
    }

    @Test
    void delete_then_return() {
        Student deleteStudent = buildStudent(5, "Roland", "Clark", "Roland", "10", "student");

        when(studentCrudRepository.findById(
                eq(deleteStudent.getNumber())
        )).thenReturn(Optional.of(deleteStudent));
        doNothing().when(studentCrudRepository).deleteById(deleteStudent.getNumber());

        Student actual = studentService.delete(deleteStudent.getNumber());

        assertEquals(actual, deleteStudent);

        Mockito.verify(
                studentCrudRepository, new Times(1)).findById(
                eq(deleteStudent.getNumber())
        );
        Mockito.verify(
                studentCrudRepository, new Times(1)).deleteById(
                eq(deleteStudent.getNumber())
        );
    }

    @Test
    void delete_then_throw_NoSuchElementException() {
        Random random = new Random();
        Integer number = random.nextInt(Integer.MAX_VALUE);

        Student deleteStudent = buildStudent(5, "Roland", "Clark", "Roland", "10", "student");

        when(studentCrudRepository.findById(
                eq(number)
        )).thenThrow(new NoSuchElementException());

        Assertions.assertThrows(NoSuchElementException.class, () -> studentService.delete(number));

        Mockito.verify(
                studentCrudRepository, new Times(1)).findById(
                eq(number)
        );
        Mockito.verify(
                studentCrudRepository, new Times(0)).save(
                eq(deleteStudent)
        );
    }

    @Test
    void deleteAll_then_return_emptyList() {
        doNothing().when(studentCrudRepository).deleteAll();

        studentService.deleteAll();

        List<Student> actualList = studentService.readAll();
        assertEquals(actualList, Collections.emptyList());

        verify(studentCrudRepository, new Times(1)).deleteAll();
    }
}