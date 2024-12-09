package by.ita.je.repository;

import by.ita.je.models.Recruiting;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitingCrudRepository extends CrudRepository<Recruiting, Integer> {

}
