package by.ita.je.services;

import by.ita.je.models.Category;
import by.ita.je.repository.CategoryCrudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryCrudRepository categoryCrudRepository;

    public Category insert(Category category) {
        return categoryCrudRepository.save(category);
    }


}
