package by.ita.je.services;

import by.ita.je.models.Course;
import by.ita.je.models.Student;
import by.ita.je.repository.StudentCrudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentCrudRepository studentCrudRepository;

    public Student findByNumber(Integer number) {
        return studentCrudRepository.findById(number).orElseThrow(() -> new NoSuchElementException(String.format("Student with number: %s not found", number)));
    }

    public Student findBySurname(String surname) {
        return StreamSupport.stream(studentCrudRepository.findByStudentSurname(surname).spliterator(), false).toList().get(0);
    }

    public List<Student> readAll() {
        return StreamSupport.stream(studentCrudRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Student insert(Student student) {
        return studentCrudRepository.save(student);
    }

    public Student update(Student student) {
        return studentCrudRepository.findById(student.getNumber())
                .map(l -> {
                    studentCrudRepository.save(student);
                    return l;
                }).orElseThrow(() -> new NoSuchElementException(String.format("Student with number: %s not found", student.getNumber())));
    }

    public Student delete(Integer number) {
        return studentCrudRepository.findById(number)
                .map(l -> {
                            studentCrudRepository.deleteById(number);
                            return l;
                        }
                ).orElseThrow(() -> new NoSuchElementException(String.format("Student with number: %s not found", number)));
    }

    public List<Student> deleteAll() {
        studentCrudRepository.deleteAll();
        return readAll();
    }

    public List<Student> findAllStudentsByCourse(Integer number) {
        return StreamSupport.stream((studentCrudRepository.findStudentsByCourse(number)).spliterator(), false)
                .collect(Collectors.toList());
    }

    public List<Course> findCompletedCourses(String surname) {

        List<Student> studentList = StreamSupport
                .stream((studentCrudRepository.findByStudentSurname(surname)).spliterator(), false).toList();
        List<Course> courseList = studentList.get(0).getCourses();

        List<Course> completedCourses = new ArrayList<>();
        for (Course course : courseList) {
            if (course.getStart().toLocalDate().isBefore(LocalDate.now())) {
                completedCourses.add(course);
            }
        }
        return completedCourses;
    }

    public List<Course> findUpcomingCourses(String surname) {

        List<Student> studentList = StreamSupport
                .stream((studentCrudRepository.findByStudentSurname(surname)).spliterator(), false).toList();
        List<Course> courseList = studentList.get(0).getCourses();

        List<Course> upcomingCourses = new ArrayList<>();
        for (Course course : courseList) {
            if (course.getStart().toLocalDate().isAfter(LocalDate.now())) {
                upcomingCourses.add(course);
            }
        }
        return upcomingCourses;
    }
}
