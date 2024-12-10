package by.ita.je.repository;

import by.ita.je.models.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCrudRepository extends CrudRepository<Student, Integer> {

    @Query(value = "SELECT * FROM STUDENT S WHERE S.NUMBER  IN " +
            "(SELECT STUDENT_NUMBER FROM COURSE_STUDENT WHERE COURSE_NUMBER =:number)", nativeQuery = true)
    public Iterable<Student> findStudentsByCourse(@Param("number") Integer number);

    @Query(value = "SELECT * FROM STUDENT S WHERE S.SURNAME =:surname", nativeQuery = true)
     Iterable<Student> findByStudentSurname(@Param("surname") String surname);
}
