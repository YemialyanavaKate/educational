package by.ita.je.services;

import by.ita.je.models.Course;
import by.ita.je.repository.CourseCrudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseCrudRepository courseCrudRepository;

    public Course findByNumber(Integer number) {
        return courseCrudRepository.findById(number).orElseThrow(() -> new NoSuchElementException(String.format("Course with number: %s not found", number)));
    }

    public List<Course> readAll() {
        return StreamSupport.stream(courseCrudRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Course insert(Course course) {
        return courseCrudRepository.save(course);
    }

    public Course update(Course course) {
        return courseCrudRepository.findById(course.getNumber())
                .map(l -> {
                    courseCrudRepository.save(course);
                    return l;
                }).orElseThrow(() -> new NoSuchElementException(String.format("Course with number: %s not found", course.getNumber())));
    }

    @Transactional
    public Course delete(Integer number) {
        return courseCrudRepository.findById(number)
                .map(l -> {
                            courseCrudRepository.deleteById(number);
                            return l;
                        }
                ).orElseThrow(() -> new NoSuchElementException(String.format("Course with number: %s not found", number)));
    }

    public List<Course> deleteAll() {
        courseCrudRepository.deleteAll();
        return readAll();
    }

    public List<Course> findByCategory(Integer categoryNumber) {
        Iterable<Course> byCategory = courseCrudRepository.findByCategory(categoryNumber);
        return StreamSupport.stream(byCategory.spliterator(), false).toList();
    }

    public List<Course> findByPrice(Integer price) {
        Iterable<Course> byPrice = courseCrudRepository.findByPrice(price);
        return StreamSupport.stream(byPrice.spliterator(), false).collect(Collectors.toList());
    }

    public List<Course> findByDuration(Integer duration) {
        Iterable<Course> byDuration = courseCrudRepository.findByDuration(duration);
        return StreamSupport.stream(byDuration.spliterator(), false).collect(Collectors.toList());
    }

    public List<Course> findByCategoryName (String category) {
        Iterable<Course> byCategory = courseCrudRepository.findByCategoryName(category);
        return StreamSupport.stream(byCategory.spliterator(), false).toList();
    }

}
