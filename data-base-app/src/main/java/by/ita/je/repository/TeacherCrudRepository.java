package by.ita.je.repository;

import by.ita.je.models.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherCrudRepository extends CrudRepository<Teacher,Integer> {
}
