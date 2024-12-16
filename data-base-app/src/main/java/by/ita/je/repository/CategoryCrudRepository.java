package by.ita.je.repository;

import by.ita.je.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryCrudRepository extends CrudRepository<Category, Integer> {


}