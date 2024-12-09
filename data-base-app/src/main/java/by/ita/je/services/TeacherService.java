package by.ita.je.services;

import by.ita.je.models.Teacher;
import by.ita.je.repository.TeacherCrudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherCrudRepository teacherCrudRepository;

    public Teacher findByNumber(Integer number) {
        return teacherCrudRepository.findById(number).orElseThrow(() -> new NoSuchElementException(String.format("Teacher with number: %s not found", number)));
    }

    public List<Teacher> readAll() {
        return StreamSupport.stream(teacherCrudRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Teacher insert(Teacher teacher) {
        return teacherCrudRepository.save(teacher);
    }

    public Teacher update(Teacher teacher) {
        return teacherCrudRepository.findById(teacher.getNumber())
                .map(l -> {
                    teacherCrudRepository.save(teacher);
                    return l;
                }).orElseThrow(() -> new NoSuchElementException(String.format("Teacher with number: %s not found", teacher.getNumber())));
    }

    public Teacher delete(Integer number) {
        return teacherCrudRepository.findById(number)
                .map(l -> {
                            teacherCrudRepository.deleteById(number);
                            return l;
                        }
                ).orElseThrow(() -> new NoSuchElementException(String.format("Teacher with number: %s not found", number)));
    }

    public List<Teacher> deleteAll() {
        teacherCrudRepository.deleteAll();
        return readAll();
    }


    public List<Teacher> findByTeacherSurname(String surname) {
        Iterable<Teacher> bySurmame = teacherCrudRepository.findByTeacherSurname(surname);
        return StreamSupport.stream(bySurmame.spliterator(), false).collect(Collectors.toList());
    }
}
