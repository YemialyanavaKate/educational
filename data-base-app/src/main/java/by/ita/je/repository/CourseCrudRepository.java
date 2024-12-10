package by.ita.je.repository;

import by.ita.je.models.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseCrudRepository extends CrudRepository<Course, Integer> {

    @Query(value = "SELECT * FROM COURSE C WHERE C.CATEGORY_NUMBER =:numberCategory", nativeQuery = true)
    public Iterable<Course> findByCategory(@Param("numberCategory") Integer numberCategory);

    @Query(value = "SELECT * FROM COURSE C WHERE C.PRICE <=:price", nativeQuery = true)
    public Iterable<Course> findByPrice(@Param("price") Integer price);

    @Query(value = "SELECT * FROM COURSE C WHERE C.DURATION  <=:duration", nativeQuery = true)
    public Iterable<Course> findByDuration(@Param("duration") Integer duration);

    @Query(value = "SELECT * FROM COURSE C WHERE C.CATEGORY_NUMBER IN" +
            "(SELECT NUMBER FROM CATEGORY WHERE CATEGORY =:category)", nativeQuery = true)
    public Iterable<Course> findByCategoryName(@Param("category") String category);





}
