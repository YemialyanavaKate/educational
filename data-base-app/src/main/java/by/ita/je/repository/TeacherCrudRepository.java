package by.ita.je.repository;

import by.ita.je.models.Teacher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherCrudRepository extends CrudRepository<Teacher, Integer> {

    @Query(value = "SELECT * FROM TEACHER T WHERE T.SURNAME =:surname", nativeQuery = true)
    public Iterable<Teacher> findByTeacherSurname(@Param("surname") String surname);

}
