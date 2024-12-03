package by.ita.je.repository;

import by.ita.je.models.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseCrudRepository extends CrudRepository<Course,Integer> {
}
