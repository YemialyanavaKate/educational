package by.ita.je.repository;

import by.ita.je.models.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCrudRepository extends CrudRepository<Student,Integer> {
}
